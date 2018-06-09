import invokeSrv from "./invoke.service";
let url = "/storeManager.do";
let doPost = function (action, query) {
    return invokeSrv.invokePostAction(url, action, query);
};
let doGet = function (action) {
    return invokeSrv.invokeGetAction(url, action);
};

export default{
    saveItemImport(query) {
        return doPost("saveItemImport", query);
    },
    ackImport(query) {
        return doPost("ackImport", query);
    },
    getItemImportMasterList(query) {
        return doPost("getItemImportMasterList", query);
    },
    getItemImportDetailList(query) {
        return doPost("getItemImportDetailList", query);
    },
    getDeliverMasterList(query) {
        return doPost("getDeliverMasterList", query);
    },
    saveDeliver(query) {
        return doPost("saveDeliver", query);
    },
    ackDeliver(query) {
        return doPost("ackDeliver", query);
    },
    getDeliverDetailList(query) {
        return doPost("getDeliverDetailList", query);
    },
    getItemDictBySoreCode(query) {
        return doPost("getItemDictBySoreCode", query);
    },
    getItemStockList(query) {
        return doPost("getItemStockList", query);
    },
    saveDefectiveProduct(query) {
        return doPost("saveDefectiveProduct", query);
    },
    ackDefectiveProduct(query) {
        return doPost("ackDefectiveProduct", query);
    },
    getDefectiveProductMasterList(query) {
        return doPost("getDefectiveProductMasterList", query);
    },
    getDefectiveProductDetailList(query) {
        return doPost("getDefectiveProductDetailList", query);
    },
    getExportMasterList(query) {
        return doPost("getExportMasterList", query);
    },
    saveExport(query) {
        return doPost("saveExport", query);
    },
    ackExport(query) {
        return doPost("ackExport", query);
    },
    getExportDetailList(query) {
        return doPost("getExportDetailList", query);
    },
    getDefectiveRepairMasterList(query) {
        return doPost("getDefectiveRepairMasterList", query);
    },
    saveDefectiveRepair(query) {
        return doPost("saveDefectiveRepair", query);
    },
    ackDefectiveRepair(query) {
        return doPost("ackDefectiveRepair", query);
    },
    getDefectiveRepairDetailList(query) {
        return doPost("getDefectiveRepairDetailList", query);
    },
    getDefectiveDamageMasterList(query) {
        return doPost("getDefectiveDamageMasterList", query);
    },
    saveDefectiveDamage(query) {
        return doPost("saveDefectiveDamage", query);
    },
    ackDefectiveDamage(query) {
        return doPost("ackDefectiveDamage", query);
    },
    getDefectiveDamageDetailList(query) {
        return doPost("getDefectiveDamageDetailList", query);
    },
}