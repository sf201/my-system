/**
 * Created by sf201 on 2017/8/25.
 */
app.service("dictSrv",["invokeSrv",function(invokeSrv) {
    this.name = "dictSrv";
    var url = "system.do";
    var doPost = function(action,query) {
        return invokeSrv.invokePostAction(url, action, query);
    };
    var doGet = function(action) {
        return invokeSrv.invokeGetAction(url, action);
    };
    //获取区域目录
    this.getAreaDict = function(query) {
        return doGet("getAreaDict");
    };
    //获取安装师傅目录
    this.getErectorDict = function(query) {
        return doGet("getErectorDict");
    };
    //获取打包价外费用目录
    this.getPriceDict = function(query) {
        return doGet("getPriceDict");
    };
    //获取仓库目录
    this.getStoreDict = function(query) {
        console.log(query);
        return doPost("getStoreDict",query);
    };
    //获取人员目录
    this.getFixReporterDict = function(query) {
        return doGet("getFixReporterDict");
    };
    //获取维修类别目录
    this.getFixTypeDict = function(query) {
        return doGet("getFixTypeDict");
    };
    //获取维修目录
    this.getFixItemDict = function(query) {
        return doGet("getFixItemDict");
    };
    //获取配置类型目录
    this.getConfigTypeDict = function(query) {
        return doGet("getConfigTypeDict");
    };
    //获取退换原因目录
    this.getExchangeReasonDict = function(query) {
        return doGet("getExchangeReasonDict");
    };
    //获取退换记录目录
    this.getExchangeRecordDict = function(query) {
        return doGet("getExchangeRecordDict");
    };
    //获取故障目录
    this.getFaultDict = function(query) {
        return doGet("getFaultDict");
    };
    //获取扣罚目录
    this.getPunishmentDict = function(query) {
        return doGet("getPunishmentDict");
    };
    //保存区域目录
    this.saveAreaDict=function(query) {
        return doPost("saveAreaDict", query);
    };
    //保存安装师傅目录
    this.saveErectorDict=function(query) {
        return doPost("saveErectorDict", query);
    };
    //保存打包价外费用目录
    this.savePriceDict=function(query) {
        return doPost("savePriceDict", query);
    };
    //保存仓库目录
    this.saveStoreDict=function(query) {
        return doPost("saveStoreDict", query);
    };
    //保存人员目录
    this.saveFixReporterDict=function(query) {
        return doPost("saveFixReporterDict", query);
    };
    //保存维修类别目录
    this.saveFixTypeDict=function(query) {
        return doPost("saveFixTypeDict", query);
    };
    //保存维修目录
    this.saveFixItemDict=function(query) {
        return doPost("saveFixItemDict", query);
    };
    //保存配置类型目录
    this.saveConfigTypeDict=function(query) {
        return doPost("saveConfigTypeDict", query);
    };
    //保存退换原因目录
    this.saveExchangeReasonDict=function(query) {
        return doPost("saveExchangeReasonDict", query);
    };
    //保存退换记录目录
    this.saveExchangeRecordDict=function(query) {
        return doPost("saveExchangeRecordDict", query);
    };
    //保存故障目录
    this.saveFaultDict=function(query) {
        return doPost("saveFaultDict", query);
    };
    //保存扣罚目录
    this.savePunishmentDict=function(query) {
        return doPost("savePunishmentDict", query);
    };
}])
