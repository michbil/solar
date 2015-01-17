from devicehive.interfaces import IProtoFactory, IProtoHandler
from datetime import datetime
from twisted.python import log
from twisted.internet.defer import Deferred, succeed, fail
from devicehive import DhError, CommandResult, BaseCommand
from zope.interface import implements, Interface, Attribute
__all__ = ['TestFactory']



def LOG_MSG(msg):
    print msg


def LOG_ERR(msg):
    log.err(msg)

class ITestOwner(Interface):
    url  = Attribute('devicehive API url')
    host = Attribute('devicehive host. it is used dring HTTP headers forming. TODO: consider to get rid of it.')
    port = Attribute('devicehive API port')

    def on_command(self, info, cmd, finish):
        """
        Processes devicehive command.

        @type info: C{object}
        @param info: Object which implements C{IDeviceInfo} interface. Specifies which device has received command.

        @type cmd: C{dict}
        @param cmd: command

        @type finish: C{Defer}
        @param finish: a user has to callback this deferred in order to signal to the library that commad has been processed.
        """

    def on_failure(self, device_id, reason):
        """
        @type device_id: C{str}
        @param device_id: device guid
        """

class TestFactory(object):
    implements(IProtoFactory,ITestOwner)
    def __init__(self, handler):
        if not IProtoHandler.implementedBy(handler.__class__) :
            raise TypeError('handler has to implement devicehive.interfaces.IProtoHandler interface.')
        self.handler = handler
        self.handler.factory = self
        self.devices = {}
        self.factories = {}
        self.timestamp = datetime.utcnow()
        self.notifications = []

    def getNotifications(self):
        return self.notifications

    def clearNotifications(self):
        self.notifications = []

    def authenticate(self, device_id, device_key):
        """
        Sends authentication message.

        @param device_id - device id
        @param device_key - device key
        @return deferred
        """
        raise NotImplementedError()

    def notify(self, notification, params, device_id = None, device_key = None):
        """
        Sends notification message to devicehive server.

        @param notification - notification identifier
        @param param - dictionary of notification parameters
        @return deferred
        """
        if (device_id is not None) and (device_id in self.devices) :
            defer = Deferred()
            def ok(res):
                LOG_MSG('Test notification has been successfully sent.')
                defer.callback(res)
            def err(reason):
                LOG_ERR('Failed to send notification.')
                defer.errback(reason)
            ok("OK")
            self.notifications.append(notification)
            return defer
        else :
            return fail(DhError('device_id parameter expected'))

    def update_command(self, command, device_id = None, device_key = None):
        """
        Updates an existing device command.

        @type command: C{obj}
        @param command: object which implements C{ICommand}

        @return deferred
        """

    def subscribe(self, device_id = None, device_key = None):
        """
        Subscribes a device to commands.

        @type device_id: C{str}
        @param device_id: device identifier (GUID)

        @type device_key: C{str}
        @param device_key: A device key. Optional parameter.

        @return deferred
        """
        if device_id in self.devices :
            defer = Deferred()
            return defer
        else :
            return fail(DhError('Failed to subscribe device "{0}".'.format(device_id)))

    def unsubscribe(self, device_id = None, device_key = None):
        """
        Unsubscribe a device from commands reception.

        @type device_id: C{str}
        @param device_id: device identifier (GUID)

        @type device_key: C{str}
        @param device_key: device name

        @return deferred
        """
       # if (device_id in self.devices) and (device_id in self.factories) :
       #    pass
       # else :
        #    return fail(DhError('device_id parameter expected'))

    def device_save(self, info):
        """
        Registers or updates a device. A valid device key is required in the deviceKey parameter
        in order to update an existing device.

        @type info: C{object}
        @param info: object which implements C{IDeviceInfo} interface

        @return deferred
        """
        self.devices[info.id] = info
        defer = Deferred()
        def registration_success(result):
            LOG_MSG('Device has been saved. Info: {0}.'.format(info))
            defer.callback(info)
        def registration_failure(reason):
            LOG_MSG('Failed to save device. Info: {0}.'.format(info))
            defer.errback(reason)
        registration_success("OK")
        return defer

    def connect(self, url):
        """
        Connects the factory to devicehive server.

        @type url: C{str}
        @param url: url to the devicehive server
        """
        self.handler.on_connected()

     # begin ITestOwner implementation
    def on_command(self, info, cmd, finish):
        if info.id in self.devices :
            self.handler.on_command(info.id, TestCommand.create(cmd), finish)
        else :
            raise ValueError('Device {0} is not registered.'.format(info.id))

    def on_failure(self, device_id, reason):
        if device_id in self.devices :
            self.handler.on_failure(device_id, reason)
    # end ITestOwner