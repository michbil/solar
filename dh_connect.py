#!/usr/bin/env python
# -*- coding: utf-8 -*-
# vim:set et tabstop=4 shiftwidth=4 nu nowrap fileencoding=utf-8:

import sys
import os
from twisted.python import log
from twisted.internet import reactor
from zope.interface import implements
from ConfigParser import ConfigParser as Conf

sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
import devicehive
import devicehive.poll
import devicehive.ws
import devicehive.auto
import devicehive.device.ws
import devicehive.interfaces
import threading
import sys

import abisolar
from abisolar import *


if sys.platform == 'darwin':
    from abisolar_test import SerialEmu
    seremu = SerialEmu()
    init_test_with(seremu)

else:
    init()


class SolarInfo(object):
    
    implements(devicehive.interfaces.IDeviceInfo)
    
    def __init__(self, config):
        self.config = config
    
    @property
    def id(self):
        return self.config.get('device', 'id')
    
    @property
    def key(self):
        return self.config.get('device', 'key')
    
    @property
    def name(self):
        return self.config.get('device', 'name')
    
    @property
    def status(self):
        return 'Online'
    
    @property
    def data(self):
        return None
    
    @property
    def network(self):
        return devicehive.Network(key = self.config.get('network', 'name'),
                                  name = self.config.get('network', 'name'),
                                  descr = self.config.get('network', 'description'))
    
    @property
    def device_class(self):
        return devicehive.DeviceClass(name = self.config.get('device_class', 'name'),
                    version = self.config.get('device_class', 'version'))

    @property
    def equipment(self):
        return [devicehive.Equipment(name = 'PV voltage', code = 'PVV', type = 'Voltage'),
                devicehive.Equipment(name = 'PV current', code = 'PVC', type = 'Amperes'),
                devicehive.Equipment(name = 'PV power', code = 'PVP', type = 'Power'),
                devicehive.Equipment(name = 'Battery Voltage', code = 'BV', type = 'Voltage'),

                devicehive.Equipment(name = 'gridVoltage', code = 'GRV', type = 'Voltage'),
                devicehive.Equipment(name = 'gridFrequency', code = 'GRF', type = 'Frequency'),

                devicehive.Equipment(name = 'OutputVoltage', code = 'OUV', type = 'Voltage'),
                devicehive.Equipment(name = 'OutputFrequency', code = 'OUF', type = 'Frequency'),

                devicehive.Equipment(name = 'OutputApparentPower', code = 'OAPP', type = 'Power'),
                devicehive.Equipment(name = 'OutputActivePower',   code = 'ACTP', type = 'Power'),

                devicehive.Equipment(name = 'outputLoadPercent',   code = 'LOAD', type = 'Percent'),
                devicehive.Equipment(name = 'pBusVoltage Voltage', code = 'BUSV', type = 'Voltage'),

                devicehive.Equipment(name = 'chargingCurrent', code = 'CHRG_CUR', type = 'Current'),
                devicehive.Equipment(name = 'batteryCapacity', code = 'BAT_CAP', type = 'Percent'),
                devicehive.Equipment(name = 'batDischargeCurrent', code = 'BAT_DISCH_CURR', type = 'Amperes'),

                devicehive.Equipment(name = 'deviceStatus', code = 'STATUS', type = 'MODE'),
                devicehive.Equipment(name = 'workMode', code = 'MODE', type = 'MODE'),
                devicehive.Equipment(name = 'settings', code = 'SETT', type = 'SETTINGS'),



        ]
    
    def to_dict(self):
        res = {'key': self.key,
               'name': self.name}
        if self.status is not None :
            res['status'] = self.status
        if self.data is not None :
            res['data'] = data
        if self.network is not None :
            res['network'] = self.network.to_dict() if devicehive.interfaces.INetwork.implementedBy(self.network.__class__) else self.network
        res['deviceClass'] = self.device_class.to_dict() if devicehive.interfaces.IDeviceClass.implementedBy(self.device_class.__class__) else self.device_class
        if self.equipment is not None :
            res['equipment'] = [x.to_dict() for x in self.equipment]
        return res


class SolarApp(object):
    
    implements(devicehive.interfaces.IProtoHandler)
    
    def __init__(self, config):
        self.factory = None
        self.info = SolarInfo(config)
        self.connected = False

    def on_apimeta(self, websocket_server, server_time):
        pass
    
    def on_closing_connection(self):
        print "Closing connection, Finishing reactor"
        reactor.stop()
    
    def on_connection_failed(self, reason):
        print "Connection,failed. Finishing reactor"
        reactor.stop()
    
    def on_failure(self, device_id, reason):
        print "Failure, Finishing reactor"
        reactor.stop()

    def timer_func(self):
        threading.Timer(60.0,self.timer_func).start();
        self.status_notify()

    def on_connected(self):

        def on_subscribe(result) :
            self.connected = True
            self.timer_func()
            def on_subsc(res):
                print '!!!! SUBSCRIBED'
            self.factory.subscribe(self.info.id, self.info.key).addCallback(on_subsc)
        def on_failed(reason) :
            log.err('Failed to save device {0}. Reason: {1}.'.format(self.info, reason))
        self.factory.device_save(self.info).addCallbacks(on_subscribe, on_failed)

    
    def on_command(self, device_id, command, finished):
        if command.command == 'refresh':
            self.status_notify();
            finished.callback(devicehive.CommandResult('Completed'))
            return
        if command.command == 'setOutputSource':
            try:
                src = int(command.parameters['source'])
                res = setOutputSource("%.2d" % src)
            except ValueError,e:
                finished.errback(NotImplementedError('wrong parameter'))
                print "caught exeption while setting output source"
                print e
                return


            finished.callback(devicehive.CommandResult(res))
            self.status_notify()
            return

        finished.errback(NotImplementedError('Unknown command {0}.'.format(command.command)))
    

    def status_notify(self):
        if self.connected :
            try:
                line_mode = query_mode();
                params = query_params();
                settings = query_settings()
            except Exception as e:
                print 'Caught exeception while status-notify'
                print e
                
            if line_mode:
                self.factory.notify('equipment',   {'equipment': 'MODE', 'state': line_mode},     self.info.id, self.info.key)

            if params:
                self.factory.notify('equipment',   {'equipment': 'PVV', 'state': params["pvInputVoltage1"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'PVP', 'state': params["pvInputPower1"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'PVC', 'state': params["pvInputCurrent"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'BV', 'state':  params["pBatteryVoltage"]},     self.info.id, self.info.key)

                self.factory.notify('equipment',   {'equipment': 'GRV', 'state': params["gridVoltageR"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'GRF', 'state': params["gridFrequency"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'OUV', 'state': params["acOutputVoltageR"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'OUF', 'state':  params["acOutputFrequency"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'OAPP', 'state': params["acOutputApparentPower"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'ACTP', 'state':  params["acOutputActivePower"]},     self.info.id, self.info.key)

                self.factory.notify('equipment',   {'equipment': 'LOAD', 'state': params["outputLoadPercent"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'BUSV', 'state': params["pBusVoltage"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'CHRG_CUR', 'state': params["chargingCurrent"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'BAT_CAP', 'state':  params["batteryCapacity"]},     self.info.id, self.info.key)

                self.factory.notify('equipment',   {'equipment': 'BAT_DISCH_CURR', 'state': params["batDischargeCurrent"]},     self.info.id, self.info.key)
                self.factory.notify('equipment',   {'equipment': 'STATUS', 'state': params["deviceStatus"]},     self.info.id, self.info.key)

            if settings:
                self.factory.notify('equipment',   {'equipment': 'SETT', 'state': settings},     self.info.id, self.info.key)
        else:
            print "not connected, sorry"



if __name__ == '__main__':
    log.startLogging(sys.stdout)
    # read conf-file
    conf = Conf()
    conf.read(os.path.join(os.path.dirname(__file__), os.path.splitext(os.path.basename(__file__))[0] + '.cfg'))
    # create device-delegate instance
    solar = SolarApp(conf)
    # Automacti factory
    # Also it is possible to use C{devicehive.poll.PollFactory} or C{devicehive.ws.WebSocketFactory}
    solar_factory = devicehive.auto.AutoFactory(solar)
    #solar_factory = devicehive.device.ws.WebSocketFactory(solar)
    #solar_factory = devicehive.poll.PollFactory(solar)
    # Send notification right after registration
    solar.status_notify()
    # Connect to device-hive
    #solar_factory.connect('ws://kidgo.com.ua:8080/DeviceHiveJava/websocket')
    devicehive.poll.RequestFactory.noisy=0
    solar_factory.connect('http://kidgo.com.ua:8080/DeviceHiveJava/rest')
    reactor.run()
