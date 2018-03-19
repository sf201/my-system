/**
 * Created by sf201 on 2017/8/25.
 */
app.service("invokeSrv",["$http","$q",function($http,$q) {
    this.name = "invokeSrv";
    //post方式通过url地址和Action名调用服务
    this.invokePostAction = function(url, action, query) {
        var deferred = $q.defer();
        $http.post(url+"?action="+action,JSON.stringify(query))
            .then(function(data) {
                deferred.resolve(data);
            }).catch(function(data) {
            deferred.reject(data);
        });
        return deferred.promise;
    };
    //get方式通过url地址和Action名调用服务
    this.invokeGetAction = function(url, action) {
        var deferred = $q.defer();
        $http.get(url+"?action="+action)
            .then(function(data) {
                deferred.resolve(data);
            }).catch(function(data) {
            deferred.reject(data);
        });
        return deferred.promise;
    };
    /**
     * 调用函数并获取函数返回结果
     * @param fn 要调用的函数
     * @param para fn函数的参数
     * @param callBack 执行后的回调函数
     */
    this.getFnData = function(fn, para, callBack) {
        if(fn&&typeof fn=="function") {

            fn(para).then(function(result) {
                if (result.data && result.data.err) {
                    layer.msg("错误："+ result.data.err);
                    callBack(null);
                } else {
                    if(callBack&&typeof callBack== "function") {
                        callBack(result.data);
                    }
                }
            }).catch(function(result) {
                layer.msg("异常："+ result.data);
                callBack(null);
            })
        }
    };
}])
