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
    saveAreaDict(query,oper='save') {
        console.log(Object.assign({oper}, {dict: query}));
        return doPost("saveAreaDict",Object.assign({oper},{dict:query}));
    }
}