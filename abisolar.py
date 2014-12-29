import serial
import time
import sys

def init():
    global ser
    if sys.platform == 'win32':
        ser = serial.Serial(port="COM1",baudrate=2400)
    else:
        ser = serial.Serial(port="/dev/ttyAMA0",baudrate=2400)

def init_test():
    global ser
    ser = {}

def init_test_with(serial_obj):
    global ser
    ser = serial_obj

POLYNOMIAL = 0x1021
PRESET = 0

def _initial(c):
    crc = 0
    c = c << 8
    for j in range(8):
        if (crc ^ c) & 0x8000:
            crc = (crc << 1) ^ POLYNOMIAL
        else:
            crc = crc << 1
        c = c << 1
    return crc

_tab = [ _initial(i) for i in range(256) ]

def _update_crc(crc, c):
    cc = 0xff & c

    tmp = (crc >> 8) ^ cc
    crc = (crc << 8) ^ _tab[tmp & 0xff]
    crc = crc & 0xffff
   # print format(crc,"02x")

    return crc

def crc(str):
    crc = PRESET
    for c in str:
        crc = _update_crc(crc, ord(c))


    chr1 = crc>>8
    chr2 = crc&0xFF

    if (chr1 == 40) or (chr1 == 13) or (chr1 == 10):
        chr1 = chr1 + 1

    if (chr2 == 40) or (chr2 == 13) or (chr2 == 10):
        chr2 = chr2 + 1

    chr1 = chr(chr1&0xFF)
    chr2 = chr(chr2&0xFF)

    return chr1+chr2

def crcb(*i):
    crc = PRESET
    for c in i:
        crc = _update_crc(crc, c)


    return crc

def dbprint(line):
        out = ""
        for c in line:
            out = out + format(ord(c),"02x")
        return out

def readline():

    result = ""
    while (1):
        time.sleep(0.001)
        count = ser.inWaiting();
        if (count > 0):
            rd = ser.read()
            for c in rd:
                result = result + c
                if ord(c) == 13:
                    print "GOT RESPONSE",result
                    return result



def query_command(cmdname,cb):

    ser.write(cmdname+crc(cmdname)+"\x0d");
    time.sleep(0.2);
    while (1):
        data = readline()
        l = len(data)

        if (l > 4):
            payload = data[0:len(data)-3];
            checksum = data[len(data)-3:len(data)-1]
            pcrc = crc(payload)
            if (checksum == pcrc):
                print "recv finish:"+payload
                return cb(payload[1:])
            else:
                print "CRC fail"
                return None
        else:
            print "Short packet"
            return None





def query_mode():
    def finish_mode(data):
        return data[0]
    return query_command("QMOD",finish_mode);


def query_settings():
    def finish_sett(data):
        vars = data.split(" ")

        outputSource = vars[16];
        chargeSource = vars[17];

        print outputSource,chargeSource

        return {"outputSource":outputSource,"chargeSource":chargeSource}
    return query_command("QPIRI",finish_sett);

def setOutputSource(val):
    print "setting out source",val
    def finish_cb(data):
        v = (data[:3] == 'ACK')
        print "From callback hello",data,v
        return data
    return query_command("POP"+val,finish_cb);

def pfloat(value):
    try:
        return float(value);
    except ValueError,e:
        print "Error occured on parsing float value",value
        return 0;

def query_params():

    def finish_p(data):
        vars = data.split(" ")

        data = ""
        finish_flag = 0;
        out = {
            "gridVoltageR": float(vars[0]),
            "gridFrequency": float(vars[1]),
            "acOutputVoltageR": float(vars[2]),
            "acOutputFrequency": float(vars[3]),
            "acOutputApparentPower": float(vars[4]),
            "acOutputActivePower": float(vars[5]),
            "outputLoadPercent": float(vars[6]),
            "pBusVoltage": float(vars[7]),
            "pBatteryVoltage": float(vars[8]),
            "chargingCurrent": float(vars[9]),
            "batteryCapacity": float(vars[10]),
            "pvInputPower1": float(vars[11]),
            "pvInputCurrent": float(vars[12]),
            "pvInputVoltage1": float(vars[13]),
            "pvInputVoltage2": float(vars [14]),
            "batDischargeCurrent": float(vars[15]),
            "deviceStatus": vars[16],

        }

        print "Out voltage ", out['acOutputVoltageR']
        print "PV power ", out['pvInputPower1'], " PV voltage ", out['pvInputVoltage1']," PV Current ",out['pvInputCurrent'],"PV power",out['pvInputCurrent']*out['pvInputVoltage1']
        print "Chargin' current %s discharge current %s" % (out['chargingCurrent'],out['batDischargeCurrent'])
        print "Apparent Power %s Active Power %s Load percent %s" % (out['acOutputApparentPower'],out['acOutputActivePower'],out['outputLoadPercent'])
        print "Battery volts %s battery percent %s" % (out['pBatteryVoltage'],out['batteryCapacity'])
        return out

    return query_command("QPIGS",finish_p);

if __name__ == "__main__":
    init()

    print query_mode()
    print query_params()
    print query_settings()
    print setOutputSource("01")