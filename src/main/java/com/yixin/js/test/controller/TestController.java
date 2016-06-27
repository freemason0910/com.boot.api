package com.yixin.js.test.controller;


import com.yixin.js.user.dao.UserRepository;
import com.yixin.js.user.model.UserEntity;
import com.yixin.js.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by apple on 16/4/6.
 */

@Controller
@RequestMapping("/account")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserRepository userDao;
    @Autowired
    UserService userService;
    @Value("${application.message:Hello Word}")
    private String message = "a";


    @RequestMapping("/index")
    public String index() {
        return ("test/index");
    }
    @RequestMapping("/test")
    public String test(Map<String, Object> model) {
        model.put("user", "user2");
        model.put("message", this.message);

        List list = new ArrayList();

        model.put("animals", list);
        Map m = new HashMap();
        m.put("1", "1");
        m.put("2", "2");

        m.put("3", "3");

        m.put("4", "4");

        model.put("map", m);
        return "test";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {

        model.put("time", new Date());
        model.put("message", this.message);
        UserEntity user = userService.findByid(1L);
        log.info("==1===");
        log.info(user.getPassWord());
        log.info(user.getUserName());
        UserEntity use = userDao.findUser(1L);
        List list = new ArrayList();
        for (Integer i = 0; i < 50; i++) {
            list.add(i);
        }
        model.put("list", list);
        log.info("================================" + use.getUserName());
        return "test/index";
    }

    @RequestMapping("/base")
    public String base(Map<String, Object> model, HttpServletRequest rep) {
        Map repmap = rep.getParameterMap();
        String name2 = rep.getParameter("username").toString();
        model.put("time", new Date());
        model.put("message", this.message);
        List list = new ArrayList();
        for (Integer i = 0; i < 50; i++) {
            list.add(i);
        }
        model.put("list", list);

        log.info("===2==");
        return "common/base";
    }
//    public  String base (Map<String, Object> model){
//        model.put("time", new Date());
//        model.put("message", this.message);
//
//        log.info("===2==");
//        return  "base" ;
//    }

    @RequestMapping("/child")
    public String child(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "child";
    }

    @RequestMapping("/file")
    public String file(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "file";
    }


    @RequestMapping("/jindutiao")
    public String jindutiao(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "jindutiao";
    }


}
