package com.yixin.js.login.controller;

import com.yixin.js.user.model.UserEntity;
import com.yixin.js.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 201603090214 on 2016/6/21.
 */
@Controller
public class LogInController {
    private static Logger log = LoggerFactory.getLogger(LogInController.class);
    @Autowired
    UserService userService;
    //转接/到登录页面
    @RequestMapping("/")
    public String tologin() {
        return "redirect:/main";
    }
    //登录页面跳转
    @RequestMapping("/main")
    public String login(Map<String, Object> model, HttpServletRequest request) {
        log.info("\n================》首页《=================\n");
        model.put("errorflog",false);
        return "views/login/login";
    }
    //登录成功判断业务权限跳转业务页面
    @RequestMapping("/successlogin")
    public String SuccessLogin(Map<String, Object> model, HttpServletRequest request) {
        log.info("\n================》登录成功《=================\n");
        request.getSession().setAttribute("request Url", request.getRequestURL());
        model.put("request Url", request.getRequestURL());
        return "redirect:/admin/manage";
    }
    //登录失败用户名密码错误提示到登录页面
    @RequestMapping("/feillogin")
    public String feillogin(Map<String, Object> model, HttpServletRequest request) {
        log.info("\n================》登录失败《=================\n");
        model.put("errorflog",true);
        model.put("errormsg","用户名密码验证失败请重新输入");
        return "views/login/login";
    }

}
