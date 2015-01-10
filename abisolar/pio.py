import sys

class FakeGPIO():
    OUT = 1
    IN = 2
    BCM = 0

    ports = [0 for x in range(32)]

    def setup(self,chan,mode):
        pass

    def setmode(self,mode):
        pass

    def input(self,chan):
        return self.ports[chan]

    def output(self,chan,val):
        self.ports[chan] = val


if sys.platform == 'linux':
    import RPi.GPIO as GPIO
else:
    GPIO = FakeGPIO()


class IO():

    def __init__(self):
        self.loads = {
            "kitchen":0,
            "fridge":1,
            "pc1":2,
        }
        self.inputs = {
            "presense1":0,
            "presense2":1
        }

        GPIO.setmode(GPIO.BCM)
        for l in self.loads:
            GPIO.setup(self.loads[l],GPIO.OUT)

        for l in self.inputs:
            GPIO.setup(self.inputs[l],GPIO.IN)


    def setLoad(self,name,value):
        if name in self.loads:
            GPIO.output(self.loads[name],value)
        else:
            raise ValueError

    def getLoad(self,name):
        if name in self.loads:
            return GPIO.input(self.loads[name])
        else:
            raise ValueError

    def getInput(self, name):
        if name in self.inputs:
            return GPIO.input(self.loads[name])
        else:
            raise ValueError
