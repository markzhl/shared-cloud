package com.github.markzhl.admin.rest.sys;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.sys.GateLog;
import com.github.markzhl.admin.service.sys.GateLogService;
import com.github.markzhl.common.util.EntityUtils;
import com.github.markzhl.vo.log.LogInfo;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("grcapi")
public class LogRest {
    @Autowired
    private GateLogService gateLogService;
    @RequestMapping(value="/log/save",method = RequestMethod.POST)
    public @ResponseBody void saveLog(@RequestBody LogInfo info){
        GateLog entity = new GateLog();
        BeanUtils.copyProperties(info,entity);
        EntityUtils.setCreateInfo(entity);
        gateLogService.insert(entity);
    }
}
