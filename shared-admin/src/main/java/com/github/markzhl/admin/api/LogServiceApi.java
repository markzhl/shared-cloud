package com.github.markzhl.admin.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.GateLog;
import com.github.markzhl.admin.service.GateLogService;
import com.github.markzhl.api.vo.log.LogInfo;

/**
 * 
 *
 * @author mark
 * @create 2017-07-01 14:39
 */
@RequestMapping("api")
@Controller
public class LogServiceApi {
    @Autowired
    private GateLogService gateLogService;
    @RequestMapping(value="/log/save",method = RequestMethod.POST)
    public @ResponseBody void saveLog(@RequestBody LogInfo info){
        GateLog log = new GateLog();
        BeanUtils.copyProperties(info,log);
        gateLogService.insertSelective(log);
    }
}
