package com.github.markzhl.admin.provider;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.GateLog;
import com.github.markzhl.admin.service.GateLogService;
import com.github.markzhl.api.ILogApi;
import com.github.markzhl.vo.log.LogInfo;

/**
 * 
 *
 * @author mark
 */
@Controller
public class LogProvider implements ILogApi{
    @Autowired
    private GateLogService gateLogService;

    public @ResponseBody void saveLog(@RequestBody LogInfo info){
        GateLog log = new GateLog();
        BeanUtils.copyProperties(info,log);
        gateLogService.insertSelective(log);
    }
}
