package cn.sf201.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sf201 on 9/5/2017.
 */
@Controller("indexController")
@RequestMapping("/index.do")
public class IndexController extends BaseAjaxController {

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("index", null);
    }
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("main", null);
    }
}
