package cn.sf201.core.filters;

import cn.sf201.core.annotations.Auth;
import cn.sf201.core.entity.user.User;
import com.alibaba.fastjson.JSON;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private void outJsonObject(HttpServletResponse response, Object obj){
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(obj));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void outJsonObjectFail(HttpServletResponse response, String errMsg) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write("{\"err\":\""+errMsg+"\"}");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                this.outJsonObjectFail(response,"您还未登录系统！");
                return false;
            }
            return true;
    }
}
