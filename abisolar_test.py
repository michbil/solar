from abisolar import *
import serial

#ser = serial.Serial(port='/dev/tty')



class SerialEmu:


    def setEmuString(self,str):
        self.string = str

    def readline(self):
        return self.string

    def write(self,text):
        l = len(text)
        cmdname = text[:(l-3)]
        print "comand", cmdname,l
        result = 'NAK'
        if cmdname == "QMOD":
            result = 'L'
        if cmdname == "QPIRI":
            result = '230.0 04.3 230.0 50.0 04.3 1000 0800 12.0 11.8 10.5 14.1 13.5 0 20 50 0 1 2 - 01 1 0 14.0 0 0'
        if cmdname == "QPIGS":
            result =  '234.0 50.0 234.0 50.0 0070 0032 007 422 13.49 00 100 0522 0000 000.1 13.50 00000 10111101 22 03 00000 100'
            #result =  '234.0 50.0 234.0 50.0 0070 0032 007 000.1 13.50 00000 10111101 22 03 00000 100'
        if cmdname[:3] == 'POP':
            result = 'ACK'
        result = '(' + result;
        result = result + crc(result)+'\n'
        print result

        self.string = result

if __name__ == "__main__":
    seremu = SerialEmu()
    init_test_with(seremu)

    print dbprint(crc('(NAK'))
