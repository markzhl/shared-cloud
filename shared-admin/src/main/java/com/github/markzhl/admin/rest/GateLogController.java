package com.github.markzhl.admin.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.GateLog;
import com.github.markzhl.admin.service.GateLogService;
import com.github.markzhl.common.msg.TableResultResponse;
import com.github.markzhl.common.rest.BaseController;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("gateLog")
public class GateLogController extends BaseController<GateLogService,GateLog> {
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<GateLog> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
        Example example = new Example(GateLog.class);
        if(StringUtils.isNotBlank(name)) {
            example.createCriteria().andLike("menu", "%" + name + "%");
        }
        int count = baseService.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<GateLog>(count,baseService.selectByExample(example));
    }
}
