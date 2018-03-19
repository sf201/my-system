package cn.sf201.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sf201 on 9/5/2017.
 */
@Controller("loginController")
@RequestMapping("/login.do")
public class LoginController extends BaseAjaxController {

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("login", null);
    }
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("redirect:/index.do");
    }

}
