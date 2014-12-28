import serial
import time

def init():
    global ser
    ser = serial.Serial(port="/dev/ttyAMA0",baudrate=2400)

def init_test():
    global ser
    ser = {}

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
    return chr(crc>>8)+chr(crc&0xFF)

def crcb(*i):
    crc = PRESET
    for c in i:
        crc = _update_crc(crc, c)


    return crc

def query_command(cmdname,cb):

    def dbprint(line):
        out = ""
        for c in line:
            if (ord(c) >= 32) and (ord(c) < 127):
                out = out + c
            else:
                out = out + " 0x"+format(ord(c),"02x")
        return out

    ser.write(cmdname+crc(cmdname)+"\x0d");
    data = ""
    while (1):
        time.sleep(0.001)
        count = ser.inWaiting();
        if (count > 0):
            rd = ser.read()

            data = data + rd
            finish_flag = 0;
            for c in data:
                if ord(c) == 13:
                    data = data[1:];
                    print "recv finish:"+dbprint(data)
                    return cb(data)


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
            "gridVoltageR": pfloat(vars[0]),
            "gridFrequency": pfloat(vars[1]),
            "acOutputVoltageR": pfloat(vars[2]),
            "acOutputFrequency": pfloat(vars[3]),
            "acOutputApparentPower": pfloat(vars[4]),
            "acOutputActivePower": pfloat(vars[5]),
            "outputLoadPercent": pfloat(vars[6]),
            "pBusVoltage": pfloat(vars[7]),
            "pBatteryVoltage": pfloat(vars[8]),
            "chargingCurrent": pfloat(vars[9]),
            "batteryCapacity": pfloat(vars[10]),
            "pvInputPower1": pfloat(vars[11]),
            "pvInputCurrent": pfloat(vars[12]),
            "pvInputVoltage1": pfloat(vars[13]),
            "pvInputVoltage2": pfloat(vars [14]),
            "batDischargeCurrent": pfloat(vars[15]),
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
    print query_mode()
    print query_params()
    print query_settings()
    print setOutputSource("01")