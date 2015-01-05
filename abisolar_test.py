from abisolar import *
import serial
import unittest
#ser = serial.Serial(port='/dev/tty')

QUERY_MODE_TESTBENCH = 'L'
QPIRI_TESTBENCH = '230.0 04.3 230.0 50.0 04.3 1000 0800 12.0 11.8 10.5 14.1 13.5 0 20 50 0 1 2 - 01 1 0 14.0 0 0'
QPIGS_TESTBENCH = '234.0 50.0 234.0 50.0 0070 0032 007 422 13.49 00 100 0522 0000 000.1 13.50 00000 10111101 22 03 00000 100'

class AbisolarTester(unittest.TestCase):

    def test_timeout(self):

        print "Testing timeout"
        ser.setNoise("Timeout")
        set_abisolar_timeout(0.1)
        def cb(input):
            return input
        self.assertEqual(query_command("QMOD",cb), None)

        print "Testing wrong CRC reception"
        ser.setNoise("CRC")
        self.assertEqual(query_command("QMOD",cb), None)



    def test_query_command(self):
        ser.setNoise("Normal")
        print "Testing query_command"
        def cb(input):
            return input
        self.assertEqual(query_command("QMOD",cb), QUERY_MODE_TESTBENCH)
        self.assertEqual(query_command("QPIRI",cb), QPIRI_TESTBENCH)
        self.assertEqual(query_command("QPIGS",cb), QPIGS_TESTBENCH)



    def test_query_mode(self):
        ser.setNoise("Normal")
        print "Testing query mode..."
        self.assertEqual(query_mode(), QUERY_MODE_TESTBENCH)

    def test_qpiri(self):
        ser.setNoise("Normal")
        print "Testing query_settings command"
        self.assertEqual(query_settings(), {'outputSource': '1', 'chargeSource': '2'})

        print "Testing trash reception"
        ser.setNoise("WrongBody")
        self.assertEqual(query_settings(), None)

    def test_qpigs(self):
        ser.setNoise("Normal")
        print "Testing qpigs command"
        wanted_result = {'pvInputVoltage2': 13.5,
                         'pvInputVoltage1': 0.1,
                         'outputLoadPercent': 7.0,
                         'pvInputPower1': 522.0,
                         'gridFrequency': 50.0,
                         'acOutputApparentPower': 70.0,
                         'deviceStatus': '10111101',
                         'chargingCurrent': 0.0,
                         'pBatteryVoltage': 13.49,
                         'acOutputFrequency': 50.0,
                         'pvInputCurrent': 0.0,
                         'batteryCapacity': 100.0,
                         'pBusVoltage': 422.0,
                         'gridVoltageR': 234.0,
                         'batDischargeCurrent': 0.0,
                         'acOutputActivePower': 32.0,
                         'acOutputVoltageR': 234.0
        }
        self.assertEqual(query_params(), wanted_result)

        print "Testing trash reception"
        ser.setNoise("WrongBody")
        self.assertEqual(query_params(), None)


class SerialEmu:

    noiseTypes = {"Normal","CRC","Timeout","WrongBody"}


    class MyException(Exception):
        pass

    def __init__(self):
        self.string = ""
        self.noiseType = "Normal"

    def setEmuString(self,str):
        self.string = str

    def setNoise(self,noiseType):
        if noiseType in self.noiseTypes:
            self.noiseType = noiseType
        else:
            raise MyException()


    def read(self,size=1):
        res =  self.string
        self.string = ""
        return res

    def write(self,text):
        l = len(text)
        cmdname = text[:(l-3)]
        print "comand", cmdname,l
        result = 'NAK'
        if cmdname == "QMOD":
            result = QUERY_MODE_TESTBENCH
        if cmdname == "QPIRI":
            result = QPIRI_TESTBENCH
        if cmdname == "QPIGS":
            result =  QPIGS_TESTBENCH
            #result =  '234.0 50.0 234.0 50.0 0070 0032 007 000.1 13.50 00000 10111101 22 03 00000 100'
        if cmdname[:3] == 'POP':
            result = 'ACK'

        if self.noiseType == "WrongBody":
            result = " fdz " + result

        result = '(' + result;
        additional = ""
        if self.noiseType == "CRC":
            additional='z' # put additional char

        result = result + crc(result+additional)+'\r'
        if self.noiseType == "Timeout":
            result = ""

        print result

        self.setEmuString(result)

    def close(self):
        print "Gracefully closing virtual port ;)"

    def inWaiting(self):
        return len(self.string)

    def flushInput(self):
        self.string = ""

if __name__ == "__main__":
    ser = SerialEmu()
    init_test_with(ser)

    unittest.main()
