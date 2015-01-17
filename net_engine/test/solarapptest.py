__author__ = 'michbil'
import os,sys
import threading
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '../..')))

from abisolar.abisolar import *
import unittest
from ConfigParser import ConfigParser as Conf
from net_engine.devhive_connection import SolarApp,SolarInfo
import devicehive.testfactory



class SolarAppTest(unittest.TestCase):
    def setUp(self):
        conf = Conf()
        confdir = ['../dh_test.cfg','../../dh_test.cfg','solarapptest.cfg']

        print os.getcwd()
        r = conf.read(confdir)
        if r == []:
            self.fail("No config found")
        else:
            print r

        self.solar = SolarApp(conf,2.0)
        self.solar_factory = devicehive.testfactory.TestFactory(self.solar)

        self.solar_factory.connect('127.0.0.1')

    def tearDown(self):
        print "Tearing down"
        self.solar.disconnect()




    def test_app(self):

        time.sleep(20)




if __name__ == "__main__":

    unittest.main()