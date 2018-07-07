import Vue from 'vue';
import moment from "moment";

Vue.filter("age", function (value, args) {
    if (value && args) {
        var timeDiffer = (args - value) / 86400000;
        if (timeDiffer < 31) {
            return timeDiffer + "天";
        }
        if (timeDiffer < 365) {
            return Math.round(timeDiffer / 31) + "月";
        }
        return Math.round(timeDiffer / 365) + "岁";
    }
});
Vue.filter("dict", function (value, args) {
    for (let i = 0; i < args.length; i++) {
        let item = args[i];
        if (item.code == value) {
            return item.name;
        }
    }
});
Vue.filter("storeDict", function (value, args) {
    for (let i = 0; i < args.length; i++) {
        let item = args[i];
        if (item.serialNo == value) {
            return item.name;
        }
    }
});
Vue.filter("itemDict", function (value, args) {
    for (let i = 0; i < args.length; i++) {
        let item = args[i];
        if (item.itemCode == value) {
            return item.itemName;
        }
    }
});
Vue.filter("staff", function (input, para) {
    if (input && para && para.length > 0) {
        for (var i = 0; i < para.length; i++) {
            if (para[i].empNo == input || para[i].userName == input) {
                return para[i].name;
            }
        }
    }
});
Vue.filter("date", function (input, fullTime) {
    let formatString = fullTime ? "YYYY-MM-DD HH:mm:ss" : "YYYY-MM-DD";
    return new moment(input).format(formatString);

});
window.Vue = Vue;//将注册有过滤器的Vue挂载到window对象中

export default {}
