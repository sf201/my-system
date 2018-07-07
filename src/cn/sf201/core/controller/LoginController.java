package cn.sf201.core.controller;

import cn.sf201.core.annotations.Auth;
import cn.sf201.core.entity.user.User;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.service.UserService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sf201 on 9/5/2017.
 */
@Controller("loginController")
@RequestMapping("/login.do")
public class LoginController extends BaseAjaxController {
    @Resource(name = "userService")
    private UserService userService;


    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = this.getPostParameters(request);
        try {
            User user = userService.login(params);
            if (user != null) {
                request.getSession().setAttribute("user", user);
            }
            this.outJsonObject(response, user);
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
       request.getSession().removeAttribute("user");
        this.outJsonObject(response, 1);
    }


}
