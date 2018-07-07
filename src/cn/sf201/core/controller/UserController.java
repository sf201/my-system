package cn.sf201.core.controller;

import cn.sf201.core.annotations.Auth;
import cn.sf201.core.entity.user.User;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller("userController")
@RequestMapping("/user-manager")
public class UserController extends BaseAjaxController {
    @Resource(name = "userService")
    private UserService userService;
    @Auth(name = "list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = this.getPostParameters(request);
        try {
            this.outJsonObject(response, userService.list(params));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    @Auth(name = "add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = this.getPostEntity(request, User.class);
        try {
            this.outJsonObject(response, userService.addUser(user));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = this.getPostParameters(request);
        try {
            this.outJsonObject(response, userService.changePassword(params));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getCurrentUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        this.outJsonObject(response, user);
    }
}
