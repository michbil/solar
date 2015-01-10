import sys,time
import os


sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '../..')))

from abisolar.pio import IO
import unittest


class IOTester(unittest.TestCase):

    def setUp(self):
        self.io = IO()

    def testLoadMgmt(self):
        print "testing load"
        for load in self.io.loads:
            self.io.setLoad(load,1)
            self.assertEquals(self.io.getLoad(load),1)
            time.sleep(0.1)
            self.io.setLoad(load,0)
            self.assertEquals(self.io.getLoad(load),0)

    def testSerialise(self):
        print "Testing serialization"
        res = self.io.serialize()
        self.assertEquals(res,{'ch1': 0, 'ch2': 0, 'ch3': 0, 'ch4': 0, 'ch5': 0, 'ch6': 0, 'ch7': 0})

        self.io.setLoad("ch1",1)
        res = self.io.serialize()
        self.assertEquals(res,{'ch1': 1, 'ch2': 0, 'ch3': 0, 'ch4': 0, 'ch5': 0, 'ch6': 0, 'ch7': 0})


if __name__ == "__main__":

    unittest.main()
