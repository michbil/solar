__author__ = 'michbil'


import os
from twisted.python import log
from twisted.internet import reactor
from zope.interface import implements
from ConfigParser import ConfigParser as Conf

import sys
import os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
import devicehive
import devicehive.poll
import devicehive.testfactory
import devicehive.ws
import devicehive.auto
import devicehive.device.ws
import devicehive.interfaces
import threading
import signal


def finishcallable():
    print "Callallble on exit called"
    close_port()
    solar.disconnect()

from abisolar.abisolar import *
from net_engine.devhive_connection import SolarApp, SolarInfo

if __name__ == '__main__':
    log.startLogging(sys.stdout)
    # read conf-file
    conf = Conf()
    conf.read(os.path.join(os.path.dirname(__file__), os.path.splitext(os.path.basename(__file__))[0] + '.cfg'))
    # create device-delegate instance
    solar = SolarApp(conf)
    solar_factory = devicehive.testfactory.TestFactory(solar)

    solar_factory.connect('http://kidgo.com.ua:8080/DeviceHiveJava/rest')
    reactor.addSystemEventTrigger('before', 'shutdown', finishcallable)
    reactor.run()