/**
 * Created by sf201 on 2017/9/6.
 */
var app = angular.module("app", ["ui.router","ui.bootstrap","ui.grid","ui.grid.edit","ui.grid.rowEdit","ui.grid.cellNav","ui.grid.saveState"])
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/");
        var states = [
                {
                    name: 'main',
                    url: '/',
                    templateUrl: 'index.do?action=main'
                },
                {
                    name: 'areaConfig',
                    url: '/area-config.html',
                    templateUrl: 'system.do?action=listArea',
                },
                {
                    name: 'erectorConfig',
                    url: '/erector-config.html',
                    templateUrl: 'system.do?action=listErector'
                },
                {
                    name: 'priceConfig',
                    url: '/price-config.html',
                    templateUrl: 'system.do?action=listPrice'
                },
                {
                    name: 'storeConfig',
                    url: '/store-config.html',
                    templateUrl: 'system.do?action=listStore'
                },
                {
                    name: 'fixReporterConfig',
                    url: '/fix-reporter-config.html',
                    templateUrl: 'system.do?action=listFixReporter'
                },
                {
                    name: 'fixTypeConfig',
                    url: '/fix-type-config.html',
                    templateUrl: 'system.do?action=listFixType'
                },
                {
                    name: 'fixItemConfig',
                    url: '/fix-item-config.html',
                    templateUrl: 'system.do?action=listFixItem'
                },
                {
                    name: 'configTypeConfig',
                    url: '/config-type-config.html',
                    templateUrl: 'system.do?action=listConfigType'
                },
                {
                    name: 'exchangeReasonConfig',
                    url: '/exchange-reason-config.html',
                    templateUrl: 'system.do?action=listExchangeReason'
                },
                {
                    name: 'exchangeRecordConfig',
                    url: '/exchange-record-config.html',
                    templateUrl: 'system.do?action=listExchangeRecord'
                },
                {
                    name: 'faultConfig',
                    url: '/fault-config.html',
                    templateUrl: 'system.do?action=listFault'
                },
                {
                    name: 'punishmentConfig',
                    url: '/punishment-config.html',
                    templateUrl: 'system.do?action=listPunishment'
                },
            ]
            ;

        angular.forEach(states, function (value, key) {
            $stateProvider.state(value);
        })

    })
    ;
