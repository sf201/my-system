/**
 * Created by sf201 on 9/7/2017.
 */
app.controller("areaCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.areaList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getAreaDict, null, function (data) {
            $scope.areaList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            $scope.gridOptions.data.push({serialNo: null, areaName: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveAreaDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'区域名称',field:'areaName',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);

app.controller("erectorCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.erectorList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getErectorDict, null, function (data) {
            $scope.erectorList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: '',phone:'',group:''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveErectorDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'姓名',field:'name',enableCellEdit:true},
            {name:'联系方式',field:'phone',enableCellEdit:true},
            {name:'组别',field:'group',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("priceCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.erectorList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getPriceDict, null, function (data) {
            $scope.erectorList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, priceName: '',units:'',price:null,memo:''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.savePriceDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'费用名称',field:'priceName',enableCellEdit:true},
            {name:'单位',field:'units',enableCellEdit:true},
            {name:'单价',field:'price',enableCellEdit:true},
            {name:'备注',field:'memo',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("storeCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getStoreDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveStoreDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'仓库名称',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("fixReporterCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getFixReporterDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveFixReporterDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'人员',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("fixTypeCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getFixTypeDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveFixTypeDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'品类',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("fixItemCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.fixTypeList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getFixItemDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });

        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: '',fixType:''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveFixItemDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        invokeSrv.getFnData(dictSrv.getFixTypeDict, null, function (data) {

            var fixTypeList = data.map( function( fixType ) { return {code:fixType.serialNo,name:fixType.name} });
            $scope.fixTypeList = fixTypeList;

            $scope.gridOptions.columnDefs = [
                {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
                {name:'序号',field:'serialNo',enableCellEdit:true},
                {name:'品类',field:'fixType',enableCellEdit:true,editableCellTemplate: 'ui-grid/dropdownEditor',
                    editDropdownIdLabel :'code',editDropdownValueLabel :'name',editDropdownOptionsArray :fixTypeList,
                cellFilter:'dict:grid.appScope.fixTypeList'},
                {name:'维修项目',field:'name',enableCellEdit:true}
            ];
        });


        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);

app.controller("configTypeCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getConfigTypeDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveConfigTypeDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'配置类型',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("exchangeReasonCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getExchangeReasonDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveExchangeReasonDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'退换原因',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("exchangeRecordCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getExchangeRecordDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: ''});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveExchangeRecordDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'退换记录',field:'name',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("faultCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getFaultDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: '',price:null});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.saveFaultDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'故障类别',field:'name',enableCellEdit:true},
            {name:'维修单价',field:'price',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);
app.controller("punishmentCtrl", ["$scope", "$uibModal", "invokeSrv", "dictSrv",
    function ($scope, $uibModal, invokeSrv, dictSrv) {
        $scope.dictList = [];
        $scope.deleteList = [];//删除的项目
        invokeSrv.getFnData(dictSrv.getPunishmentDict, null, function (data) {
            $scope.dictList = data;
            $scope.gridOptions.data=data;
        });
        $scope.add = function () {
            if($scope.gridOptions.data==undefined||$scope.gridOptions.data==null) {
                $scope.gridOptions.data = [];
            }
            $scope.gridOptions.data.push({serialNo: null, name: '',costs:null});
        }
        $scope.save=function() {
            var array = $scope.gridApi.rowEdit.getDirtyRows();
            var saveArray = array.map( function( gridRow ) { return gridRow.entity; });
            var saveVo = {};
            saveVo.addList = saveArray;
            saveVo.delList = $scope.deleteList;
            dictSrv.savePunishmentDict(saveVo).then(function(result) {
                if(result.data&&result.data.err) {
                    layer.msg("保存失败，错误信息："+err);
                }else {
                    $scope.gridApi.rowEdit.setRowsClean(saveArray);
                    layer.msg("保存成功！");
                }
            }).catch(function(result) {
                layer.msg("保存失败，出现异常，请联系管理员!");
            })
        };
        $scope.delete=function() {

            for(var i = $scope.gridOptions.data.length - 1;i>=0;i--) {
                var item = $scope.gridOptions.data[i];
                if(item.selected) {
                    if(item.id) {
                        $scope.deleteList.push(item.id);
                    }
                    $scope.gridOptions.data.splice(i, 1);
                }
            }
        };
        $scope.gridOptions = {rowEditWaitInterval:-1};
        $scope.gridOptions.columnDefs = [
            {name:'',field:'selected',width:30,type:'boolean',cellTemplate: '<input type="checkbox" ng-model="row.entity.selected">'},
            {name:'序号',field:'serialNo',enableCellEdit:true},
            {name:'扣罚类别',field:'name',enableCellEdit:true},
            {name:'金额',field:'costs',enableCellEdit:true}
        ];

        $scope.gridOptions.onRegisterApi = function(gridApi){
            //set gridApi on scope
            $scope.gridApi = gridApi;
        };
    }]);

