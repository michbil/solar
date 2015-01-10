import serial
import time
import sys
import io
from crc import crc

abisolar_timeout = 5.0
base_delay=0.1

def set_abisolar_timeout(t,d):
    global abisolar_timeout,base_delay
    abisolar_timeout = t
    base_delay = d


def init():
    global ser
    if sys.platform == 'win32':
        comport = 'COM1'
    else:
        comport = "/dev/ttyAMA0"
    ser = serial.Serial(port=comport, baudrate=2400, timeout=5.0)


def init_test():
    global ser
    ser = {}


def init_test_with(serial_obj):
    global ser
    ser = serial_obj


def close_port():
    ser.close()


def dbprint(line):
    out = ""
    for c in line:
        out = out + format(ord(c), "02x")
    return out


def readline():
    global abisolar_timeout
    now = time.time()

    result = ""
    while (time.time() - now) < abisolar_timeout:
        time.sleep(0.1)
        count = ser.inWaiting()
        if (count > 0):
            rd = ser.read(size=count)
            for c in rd:
                result = result + c
                if ord(c) == 13:
                    # print "GOT RESPONSE",result
                    ser.flushInput()
                    return result
    print "Timeout triggered"
    return None


def query_command_once(cmdname, cb):
    ser.write(cmdname + crc(cmdname) + "\x0d");
    time.sleep(base_delay);

    data = readline()

    if data is None:
        return None

    l = len(data)

    if (l > 4):
        payload = data[0:len(data) - 3];
        checksum = data[len(data) - 3:len(data) - 1]
        pcrc = crc(payload)
        if (checksum == pcrc):
            # print "recv finish:"+payload
            return cb(payload[1:])
        else:
            print "CRC fail"
            return None
    else:
        print "Short packet"
        return None


def query_command(cmdname, cb):
    res = None
    for i in range(1, 2):
        res = query_command_once(cmdname, cb)
        if res:
            return res
        print "Retry number", i
        time.sleep(base_delay)
    print "No valid responce after retries"
    return None


def query_mode():
    def finish_mode(data):
        if (len(data) == 1) and data[0] in 'PSLBFD':
            return data[0]
        else:
            return None

    return query_command("QMOD", finish_mode);


def query_settings():
    def finish_sett(data):
        vars = data.split(" ")
        try:
            outputSource = vars[16]
            chargeSource = vars[17]
            if (int(outputSource) in range(0,5)) and (int(chargeSource) in range(0,5)):
                print outputSource, chargeSource
                return {"outputSource": outputSource, "chargeSource": chargeSource}
            else:
                print "Some shitty info got"
                return None
        except ValueError:
            print "Trash found in package"
            return None



    return query_command("QPIRI", finish_sett);


def setOutputSource(val):
    print "setting out source", val

    def finish_cb(data):
        v = (data[:3] == 'ACK')
        print "From callback hello", data, v
        return data

    return query_command("POP" + val, finish_cb);


def pfloat(value):
    try:
        return float(value);
    except ValueError, e:
        print "Error occured on parsing float value", value
        return 0;


def query_params():
    def finish_p(data):
        vars = data.split(" ")

        data = ""
        finish_flag = 0;
        try:
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
                "pvInputVoltage2": float(vars[14]),
                "batDischargeCurrent": float(vars[15]),
                "deviceStatus": vars[16],

            }
        except ValueError:
            print "Trash found in package"
            return None

        print "Out voltage ", out['acOutputVoltageR']
        print "PV power ", out['pvInputPower1'], " PV voltage ", out['pvInputVoltage1'], " PV Current ", out[
            'pvInputCurrent'], "PV power", out['pvInputCurrent'] * out['pvInputVoltage1']
        print "Chargin' current %s discharge current %s" % (out['chargingCurrent'], out['batDischargeCurrent'])
        print "Apparent Power %s Active Power %s Load percent %s" % (
            out['acOutputApparentPower'], out['acOutputActivePower'], out['outputLoadPercent'])
        print "Battery volts %s battery percent %s" % (out['pBatteryVoltage'], out['batteryCapacity'])
        return out

    return query_command("QPIGS", finish_p);


if __name__ == "__main__":
    init()
    print "Checking query_mode command...."
    print query_mode()
    print "Checking query_params command...."
    print query_params()
    print "Checking query_settings command...."
    print query_settings()
    print "Checking set output source command...."
    print setOutputSource("01")