
outputModes = {
    'Utility': '00',
     'Solar': '01',
     'SBU': '02',
}
chargeSources = {
    'Utility':"00",
    'Solar first':"01",
    'Utility and Solar':"02",
    'Solar only':"03"
}

getKeyByValue = function( obj, value ) {
    var i = 0;
    for( var prop in obj ) {
        if (i==value) return prop;
       i++;
    }
};

var loginG,passwordG;

var MainCtrl = function($scope) {

    $scope.pvVoltage="...";
    $scope.pvCurrent="...";

    $scope.login = "";
    $scope.password = "";
    $scope.online = "...";

    $scope.checkModel = {
        ch1:false,
        ch2:false,
        ch3:false,
        ch4:false,
        ch5:false,
        ch6:false,
        ch7:false

    }

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

    // register time of last valid notification
    function register_time (time) {
        t = Date.parse(time);
        oldstamp = $scope.timestamp;
        if (oldstamp == undefined)  {
            oldstamp = 0;
        } else {
            oldstamp = Date.parse(oldstamp)
        }
        if (oldstamp == undefined) oldstamp = 0;
        if (t > oldstamp) {
            $scope.timestamp=time;
        }

    }
    function recalc_time () {

        now = Date.now();
        then = Date.parse($scope.timestamp);
        var seconds = (now - then) / 1000;
        console.log(formatTime(seconds));
        $scope.online=formatTime(seconds);
        $scope.$apply()


    }

    // handles incoming notification
    $scope.handleNotification = function (deviceId, notification) {
        register_time(notification.timestamp)

        if (notification.notification == "equipment") {
            decode_eq(notification.parameters.equipment,notification.parameters.state);
        }
        else if (notification.notification == "$device-update") {
            if (notification.parameters.status) this.device.status = notification.parameters.status;
            if (notification.parameters.name) this.device.name = notification.parameters.name;
            this.updateDeviceInfo(this.device);
            recalc_time();
        }
    }

    // decode incoming equipment codes
     function decode_eq(code,p) {
         console.log("Notification inconming "+code+" "+p)
             switch (code) {
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
                 case 'SETT':
                     $scope.settings = p;
                     $scope.chargePri = getKeyByValue(chargeSources,parseInt($scope.settings['chargeSource']))
                     $scope.loadPri = getKeyByValue(outputModes,parseInt($scope.settings['outputSource']))
                     break;
                 case 'STATUS':
                     $scope.deviceStatus = p;
                     workInfo.decode_status(p);
                     $scope.loadSource = workInfo.loadSource;
                     $scope.chargeSource = workInfo.chargeSource;
                     break;
                 case 'LS':
                     for (i in p) {
                         $scope.checkModel[i] = (p[i] == 1)
                     }

                     break;


             }
         $scope.$apply()


     }

    // get equipment states
    $scope.getEq = function () {

        $scope.deviceHive.getEquipmentState($scope.device.id)
            .done(function (data) {
                jQuery.each(data, function (index, equipment) {
                    var p = equipment.parameters.state;
                    decode_eq(equipment.id,p);
                    register_time(equipment.timestamp);
                });
                $scope.$apply();
            })
            .fail($scope.handleError);
        }

    // default errror handler stub
    $scope.handleError = function (e) {
        alert("error "+e)
    }





    $scope.subscribe = function () {

        $scope.updateDeviceInfo();
        $scope.getEq();
        $scope.subscribeNotifications();


    }
    $scope.sendRefresh = function () {
        result = $scope.deviceHive.sendCommand($scope.device.id, "refresh", {})
        $('.refreshajax').show();
        result.result(function(res) {
            console.log(res);
            $('.refreshajax').hide();
        });

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


            loginG = $scope.login;
            passowrdG = $scope.password;

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


    };


    $scope.setConnection();
    $scope.changeLoad = function (val) {
        $('#load_ajax').html('<img src="img/ajax-loader.gif">');
        console.log(val+" "+outputModes[val])
        result = $scope.deviceHive.sendCommand($scope.device.id, "setOutputSource", {"source":outputModes[val]})
        result.result(function(res) {
            if (res.status == 'ACK') {
                result = 'OK'
            } else {
                result = "Ошибка"
            }
            $('#load_ajax').html(result)

        });
    };
    $scope.setLoad = function(v) {
        console.log($scope.checkModel[v])
        result = $scope.deviceHive.sendCommand($scope.device.id, "setLoad", {"name":v,"value":$scope.checkModel[v]})
        result.result(function(res) {

        });
    };
    setInterval(recalc_time,3000);

};


var GraphCtrl = function ($scope) {

    $scope.login = window.localStorage.getItem("login");
    $scope.password = window.localStorage.getItem("password");
    $scope.power = 0;

    var request = superagent;
    var csv = 'Date,Power\n';

    $scope.buildGraph = function () {
        r = request
            .get('http://kidgo.com.ua:8080/DeviceHiveJava/rest/device/E50D6085-2ABA-48E9-B1C3-73C673E414B1/notification?take=20000&sortOrder=DESC')
            .auth($scope.login, $scope.password)
            .end(function(err,res) {
                if (err) {
                    console.log ("error",err);
                    return;
                }
                //console.log("Got",res.text);
                res.text = JSON.parse(res.text);
                var mode = 0;var ts;
                for (i=0;i<res.text.length;i++) {
                    el = res.text[i];
                    if (el.parameters.equipment == 'PVC') {
                        mode = el.parameters.state;
                        ts = el.timestamp;
                    }
                    if (el.parameters.equipment == 'PVV') {
                        var v = el.parameters.state;
                        var curr = mode;
                        //console.log(sprintf('PVC %6d      PVV %6d             P %6d           %20s %10s',v,curr,curr*v,el.timestamp,ts));
                        csv+= el.timestamp+','+curr*v+'\n';
                        $scope.power += (curr*v/60)/1000;
                        //console.log($scope.power);
                        //console.log(v,curr,curr*v,el.timestamp,ts);
                    }
                }
                console.log(csv);
                $scope.power *= 0.9;
                g = new Dygraph(

                    // containing div
                    document.getElementById("graphdiv"), csv

                );
                $scope.$apply();


            });
    }

};

app.config(function ($routeProvider,$locationProvider) {

    $routeProvider.when("/", {
        templateUrl:"monitor.html",
        controller: MainCtrl
    });
    $routeProvider.when("/graph", {
        templateUrl:"grpah.html",
        controller: GraphCtrl
    });

});



app = app.controller('main', ['$scope', MainCtrl]);
