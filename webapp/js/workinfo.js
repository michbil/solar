
function WorkInfo() {

}

WorkInfo.prototype = {
    setCustomerV: function (v) {
        this.v = v;
    },
    setHasLoad: function (v) {
        this.load = v;
    },
    setChargeOn: function (v) {
        this.charge = v;
    },
    setSCCvargeOn:function (v) {
        this.ssccharge = v;
    },
    setACvargeOn:function (v) {
        this.accharge = v;
    },
    setChargeSource: function (v) {
        this.chargeSource = v;
        console.log(this.chargeSource);
    },
    setLoadSource:function (v) {
        this.loadSource = v;
        console.log(this.loadSource);
    },
    isChargeOn:function (v) {
        return this.charge;
    },
    isSCCvargeOn:function (v) {
        return this.ssccharge;
    },
    isACvargeOn:function (v) {
        return this.accharge;
    },
    isHasLoad:function (v) {
        return this.load;
    },
    getWorkMode: function () {
        return this.workMode;
    },
    decodeWorkMode: function (qmodStr) {
        var workMode = "Standby Mode";
        if (qmodStr=="P") {
            workMode = "Power On Mode";
        } else if (qmodStr=="S") {
            workMode = "Standby Mode";
        } else if (qmodStr=="L") {
            workMode = "Line Mode";
        } else if (qmodStr=="B") {
            workMode = "Battery Mode";
        } else if (qmodStr=="F") {
            workMode = "Fault Mode";
        } else if (qmodStr=="D") {
            workMode = "Shutdown Mode";
        }
        this.workMode = workMode;
        console.log(workMode);
    },
    decode_status: function (deviceStatus) {

        var ch0 = deviceStatus.charAt(0);
        if (ch0 == '1') {
            this.setCustomerV("1");
        } else {
            this.setCustomerV("0");
        }
        var ch1 = deviceStatus.charAt(1);
        if (ch1 == '1')
        {
    //            getProtocol().setDelayChanging(true);
    //            queryMachineInfo();
    //            queryCapability();
    //            queryConfigData();
        }
        var ch2 = deviceStatus.charAt(2);
        if (ch2 == '1') {
            //queryMachineInfo();
        }
        var ch3 = deviceStatus.charAt(3);
        if (ch3 == '1') {
            this.setHasLoad(true);
        } else {
            this.setHasLoad(false);
        }
        var ch5 = deviceStatus.charAt(5);
        if (ch5 == '1') {
            this.setChargeOn(true);
        } else {
            this.setChargeOn(false);
        }
        var ch6 = deviceStatus.charAt(6);
        if (ch6 == '1') {
            this.setSCCvargeOn(true);
        } else {
            this.setSCCvargeOn(false);
        }
        var ch7 = deviceStatus.charAt(7);
        if (ch7 == '1') {
            this.setACvargeOn(true);
        } else {
            this.setACvargeOn(false);
        }
        if (this.isChargeOn())
        {
            if ((this.isSCCvargeOn()) && (this.isACvargeOn())) {
                this.setChargeSource("Solar and Utility");
            } else if (this.isSCCvargeOn()) {
                this.setChargeSource("Solar");
            } else if (this.isACvargeOn()) {
                this.setChargeSource("Utility");
            }
        }
        else {
            this.setChargeSource("---");
        }
        if (this.isHasLoad())
        {
            if (this.workMode == "Line Mode") {
                this.setLoadSource("Utility");
            } else if (this.workMode == "Battery Mode") {
                this.setLoadSource("Battery");
            }
        }
        else {
            this.setLoadSource("---");
        }



    }


}
workInfo = new WorkInfo()

