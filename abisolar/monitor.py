import sys,os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from abisolar.abisolar import *

if sys.platform == 'darwin':
    from abisolar.test.abisolar_test import SerialEmu
    seremu = SerialEmu()
    init_test_with(seremu)

else:
    init()

class Monitor():

    def __init__(self):
        self.line_mode = None
        self.params = None
        self.settings = None

    def sync_vars(self):
        line_mode=None
        params=None
        settings=None
        try:
            line_mode = query_mode();
            params = query_params();
            settings = query_settings()
        except Exception as e:
            print 'Caught exeception while status-notify'
            print e

        self.line_mode = line_mode
        self.params = params
        self.settings = settings

if __name__ == "__main__":
    mon = Monitor()
    mon.sync_vars()


