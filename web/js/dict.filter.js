/**
 * Created by sf201 on 9/25/2017.
 */
app.filter("dict",function() {
    return function(input,args) {
        if (!input||!args){
            return '';
        } else {
            for(var i=0;i<args.length;i++) {
                if(args[i].code==input) {
                    return args[i].name;
                }
            }
            return '';
        }
    };

})