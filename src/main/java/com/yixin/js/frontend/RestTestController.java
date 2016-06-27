package com.yixin.js.frontend;


import com.alibaba.fastjson.JSONObject;
import com.yixin.js.common.model.SysLogEntity;
import com.yixin.js.common.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by apple on 16/5/7.
 * EnableAutoConfiguration : 进行默认加载
 */
@RestController
public class RestTestController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping(value = "/as", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getAll(@RequestBody JSONObject data) throws Exception {

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name",namespace);
//        jsonObject.put("set",set);
//        jsonObject.put("key",keyvalue);

        SysLogEntity sysLogEntity = new SysLogEntity();
        sysLogEntity.setCreateTime(new Date());
        sysLogEntity.setCreateBy("zhaoming");
        sysLogEntity.setUserAgent("shiyue");
        sysLogEntity.setType(data.getString("type"));
        sysLogEntity.setRequertUri("RestTestController:getAll");
        sysLogEntity.setRemoteAddr("localhost");
        sysLogEntity.setParams("123");
        sysLogEntity.setMethod("get");
        sysLogEntity.setException("");

        sysLogService.save(sysLogEntity);

        return jsonObject;
    }

    @RequestMapping(value = "/h")
    String home() {
        return "I     Love You";
    }


}
