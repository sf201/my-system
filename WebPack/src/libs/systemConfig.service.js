import invokeSrv from "./invoke.service";

let url = "/system.do";
let doPost = function (action, query) {
    return invokeSrv.invokePostAction(url, action, query);
};
let doGet = function (action) {
    return invokeSrv.invokeGetAction(url, action);
};
export default {
    getAreaDict() {
        return doGet("getAreaDict");
    },
    saveAreaDict(query, oper = 'save') {
        return doPost("saveAreaDict", Object.assign({oper}, {dict: query}));
    },
    getErectorDict() {
        return doGet("getErectorDict");
    },
    saveErectorDict(query, oper = 'save') {
        return doPost("saveErectorDict", Object.assign({oper}, {dict: query}));
    },
    getPriceDict() {
        return doGet("getPriceDict");
    },
    savePriceDict(query,oper='save') {
        return doPost("savePriceDict", Object.assign({oper}, {dict: query}));
    },
    getStoreDict(query) {
        return doPost("getStoreDict",query);
    },
    saveStoreDict(query,oper='save') {
        return doPost("saveStoreDict", Object.assign({oper}, {dict: query}));
    },
    getFixReporterDict() {
        return doGet("getFixReporterDict");
    },
    saveFixReporterDict(query,oper='save') {
        return doPost("saveFixReporterDict", Object.assign({oper}, {dict: query}));
    },
    getFixTypeDict() {
        return doGet("getFixTypeDict");
    },
    saveFixTypeDict(query,oper='save') {
        return doPost("saveFixTypeDict", Object.assign({oper}, {dict: query}));
    },
    getFixItemDict() {
        return doGet("getFixItemDict");
    },
    saveFixItemDict(query,oper='save') {
        return doPost("saveFixItemDict", Object.assign({oper}, {dict: query}));
    },
    getItemDict() {
        return doGet("getItemDict");
    },
    saveItemDict(query,oper='save') {
        return doPost("saveItemDict", Object.assign({oper}, {dict: query}));
    },
    getConfigTypeDict() {
        return doGet("getConfigTypeDict");
    },
    saveConfigTypeDict(query,oper='save') {
        return doPost("saveConfigTypeDict", Object.assign({oper}, {dict: query}));
    },
    getExchangeReasonDict() {
        return doGet("getExchangeReasonDict");
    },
    saveExchangeReasonDict(query,oper='save') {
        return doPost("saveExchangeReasonDict", Object.assign({oper}, {dict: query}));
    },
    getExchangeRecordDict() {
        return doGet("getExchangeRecordDict");
    },
    saveExchangeRecordDict(query,oper='save') {
        return doPost("saveExchangeRecordDict", Object.assign({oper}, {dict: query}));
    },
    getFaultDict() {
        return doGet("getFaultDict");
    },
    saveFaultDict(query,oper='save') {
        return doPost("saveFaultDict", Object.assign({oper}, {dict: query}));
    },
    getPunishmentDict() {
        return doGet("getPunishmentDict");
    },
    savePunishmentDict(query,oper='save') {
        return doPost("savePunishmentDict", Object.assign({oper}, {dict: query}));
    },
    getHouseResource(query) {
        return doPost("getHouseResource",query);
    },
    saveHouseResource(query,oper='save') {
        return doPost("saveHouseResource", Object.assign({oper}, {dict: query}));
    },
    getExportTypeDict() {
        return doGet("getExportTypeDict");
    },
    saveExportTypeDict(query,oper='save') {
        return doPost("saveExportTypeDict", Object.assign({oper}, {dict: query}));
    }
};