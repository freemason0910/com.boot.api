package com.yixin.js.admin.controller;

import com.yixin.js.user.dao.UserRepository;
import com.yixin.js.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 201603090214 on 2016/6/23.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/manage")
    public String index(Map<String, Object> mo, HttpServletRequest request) {
        List list = new ArrayList();
        for (Integer i = 0; i < 50; i++) {
            UserEntity user =new UserEntity();
            user.setUserName(i+"name");
            user.setUserId(i+"pw");
            list.add(user);
        }
        mo.put("list",list);
        mo.put("admin","王");
        return ("views/admin/manage");
    }
}
