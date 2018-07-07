import axios from 'axios';
import Vue from "vue";
import config from '../../build/config';
import Cookies from "js-cookie";

axios.interceptors.response.use((response) => {
    if (response.data && response.data.err) {
        if (response.data.err == "您还未登录系统！") {
            Cookies.remove('user');
            Cookies.remove('password');
            Cookies.remove('hasGreet');
            Cookies.remove('access');
            window.vueMain.$router.push({name: 'login'});
        }
    }
    return response;
});
export default {
    invokeGetAction(url, action) {
        if (config.env === 'development') {
            url = '/api' + url;
        }
        return axios.get(url + "?action=" + action);
    },
    invokePostAction(url, action, query) {
        if (config.env === 'development') {
            url = '/api' + url;
        }
        return axios.post(url + "?action=" + action, query);
    },
    getFnData(fn, para, callBack) {
        if (fn && typeof fn == "function") {
            fn(para).then(function (result) {
                if (result.data && result.data.err) {
                    new Vue().$Message.error(result.data.err);
                    callBack(null);
                } else {
                    if (callBack && typeof callBack == "function") {
                        callBack(result.data);
                    }
                }
            }).catch(function (result) {
                console.log("catch");
                console.log(result);
                new Vue().$Message.error(result);
                callBack(null);
            })
        }
    }
}