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


if __name__ == "__main__":

    unittest.main()
