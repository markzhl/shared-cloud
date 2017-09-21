package com.github.markzhl.admin.controller.sys;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.markzhl.admin.entity.sys.BaseUser;
import com.github.markzhl.admin.service.sys.BaseUserService;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.msg.TableResultResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/baseUser")
public class BaseUserController extends BaseController<BaseUserService,BaseUser> {
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<BaseUser> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
    	Page<BaseUser> page = new Page<BaseUser>();
    	page.setSize(limit);
    	page.setCurrent(offset);
    	Wrapper<BaseUser> wrapper = new EntityWrapper<BaseUser>(new BaseUser());
    	if(StringUtils.isNotBlank(name)){
    		wrapper.like("name", "%" + name + "%").or().
    		like("username", "%" + name + "%");
    	}
    	page = baseService.selectPage(page, wrapper);
        return new TableResultResponse<BaseUser>(page.getRecords().size(),page.getRecords());
    }
}
