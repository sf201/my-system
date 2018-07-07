import invokeSrv from "./invoke.service";
let loginUrl = "/login.do";
let url="/user-manager.do"
let doPost = function (action, query) {
    return invokeSrv.invokePostAction(url, action, query);
};
let doLoginPost = function (action, query) {
    return invokeSrv.invokePostAction(loginUrl, action, query);
};
let doGet = function (action) {
    return invokeSrv.invokeGetAction(url, action);
};

export default{
    add(query) {
        return doPost("add", query);
    },
    list(query) {
        return doPost("list", query);
    },
    getCurrentUser(query) {
        return doPost("getCurrentUser", query);
    },
    changePassword(query) {
        return doPost("changePassword", query);
    },
    login(query) {
        return doLoginPost("login", query);
    },
    logout(query) {
        return doLoginPost("logout", query);
    },
}