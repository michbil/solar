
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
    }

}
workInfo = new WorkInfo()


var MainCtrl = function($scope) {

    $scope.pvVoltage="init";
    $scope.pvCurrent="init";

    $scope.login = ""
    $scope.password = ""


    $scope.updateDeviceInfo = function () {
        $scope.name = $scope.device.name;
        $scope.status = $scope.device.status;
    }

    $scope.subscribeNotifications = function () {
        var that = this;
        $scope.deviceHive.channelStateChanged(function (data) {
            //$scope.updateChannelState(data.newState);
        });
        $scope.deviceHive.notification(function () {
            $scope.handleNotification.apply(that, arguments);
        });
        $scope.deviceHive.openChannel()
            .done(function() { $scope.deviceHive.subscribe($scope.device.id); })
            .fail(that.handleError);
    }

    // handles incoming notification
    $scope.handleNotification = function (deviceId, notification) {
        if (notification.notification == "equipment") {
            if (notification.parameters.equipment == "LED") this.updateLedState(notification.parameters.state);
        }
        else if (notification.notification == "$device-update") {
            if (notification.parameters.status) this.device.status = notification.parameters.status;
            if (notification.parameters.name) this.device.name = notification.parameters.name;
            this.updateDeviceInfo(this.device);
        }
    }


     function decode_eq(data) {
         jQuery.each(data, function (index, equipment) {
             var p = equipment.parameters.state;
             switch (equipment.id) {
                 case "MODE":
                     workInfo.decodeWorkMode(p);
                     $scope.workMode = workInfo.getWorkMode();
                     break;
                 case 'PVV':$scope.pvVoltage = p; break;
                 case 'PVC':$scope.pvCurrent = p; break;

                 case 'PVP':$scope.pvPower = p; break;
                 case 'BV': $scope.batteryVoltage = p; break;

                 case 'GRV':$scope.gridVoltage = p; break;
                 case 'GRF':$scope.gridFrequency = p; break;


                 case 'OUV':$scope.outputVoltage = p; break;
                 case 'OUF':$scope.outputFrequency = p; break;


                 case 'OAPP':$scope.outputApparentPower = p; break;
                 case 'ACTP':$scope.outputActivePower = p; break;

                 case 'LOAD':$scope.outputLoadPercent = p; break;
                 case 'BUSV':$scope.pBusVoltage = p; break
                 case 'BV':$scope.batteryVoltage = p; break;



                 case 'CHRG_CUR':$scope.chargingCurrent = p; break;
                 case 'BAT_CAP':$scope.batteryCapacity = p; break;

                 case 'BAT_DISCH_CURR':$scope.batDischargeCurrent = p; break;
                 case 'STATUS':
                     $scope.deviceStatus = p;
                     decode_status(p);
                     break;


             }
         });

     }

    function decode_status(deviceStatus) {



        var ch0 = deviceStatus.charAt(0);
        if (ch0 == '1') {
            workInfo.setCustomerV("1");
        } else {
            workInfo.setCustomerV("0");
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
            queryMachineInfo();
        }
        var ch3 = deviceStatus.charAt(3);
        if (ch3 == '1') {
            workInfo.setHasLoad(true);
        } else {
            workInfo.setHasLoad(false);
        }
        var ch5 = deviceStatus.charAt(5);
        if (ch5 == '1') {
            workInfo.setChargeOn(true);
        } else {
            workInfo.setChargeOn(false);
        }
        var ch6 = deviceStatus.charAt(6);
        if (ch6 == '1') {
            workInfo.setSCCvargeOn(true);
        } else {
            workInfo.setSCCvargeOn(false);
        }
        var ch7 = deviceStatus.charAt(7);
        if (ch7 == '1') {
            workInfo.setACvargeOn(true);
        } else {
            workInfo.setACvargeOn(false);
        }
        if (workInfo.isChargeOn())
        {
            if ((workInfo.isSCCvargeOn()) && (workInfo.isACvargeOn())) {
                workInfo.setChargeSource("Solar and Utility");
            } else if (workInfo.isSCCvargeOn()) {
                workInfo.setChargeSource("Solar");
            } else if (workInfo.isACvargeOn()) {
                workInfo.setChargeSource("Utility");
            }
        }
        else {
            workInfo.setChargeSource("---");
        }
        if (workInfo.isHasLoad())
        {
            if (workInfo.workMode == "Line Mode") {
                workInfo.setLoadSource("Utility");
            } else if (workInfo.workMode == "Battery Mode") {
                workInfo.setLoadSource("Battery");
            }
        }
        else {
            workInfo.setLoadSource("---");
        }

        $scope.loadSource = workInfo.loadSource;
        $scope.chargeSource = workInfo.chargeSource;

}

    $scope.getEq = function () {

    $scope.deviceHive.getEquipmentState($scope.device.id)
        .done(function (data) {
            decode_eq(data);
            $scope.$apply();
        })
        .fail($scope.handleError);
    }

    $scope.handleError = function (e) {
        alert("error "+e)
    }




    $scope.subscribe = function () {

        $scope.updateDeviceInfo();
        $scope.getEq();
        $scope.subscribeNotifications();


    }

    $scope.setConnection = function (log,pass) {

        if ((log == undefined) && (pass == undefined)) {
            $scope.login = window.localStorage.getItem("login")
            $scope.password = window.localStorage.getItem("password")
        } else {
            $scope.login = log;
            $scope.password = pass;
            window.localStorage.setItem("login",$scope.login)
            window.localStorage.setItem("password",$scope.password)
        }

        if (($scope.login != undefined) && ($scope.password != undefined))  {


            $scope.deviceHive = new DeviceHive("http://kidgo.com.ua:8080/DeviceHiveJava/rest", $scope.login, $scope.password);

            $scope.deviceHive.getDevice("E50D6085-2ABA-48E9-B1C3-73C673E414B1").done(function (result) {

                $scope.login_not_done = undefined;
                $scope.device = result
                console.log("Device found")
                $scope.subscribe()
                $scope.$apply();




//            app.start(deviceHive,result);

            }).fail(function() {
                console.log("device not found");
                $scope.login_not_done = "enter login";
                $scope.$apply();

            }); // get devices
        } else {
            $scope.login_not_done = "enter login";
        }


    }


    $scope.setConnection();

};

app = app.controller('main', ['$scope', MainCtrl]);
