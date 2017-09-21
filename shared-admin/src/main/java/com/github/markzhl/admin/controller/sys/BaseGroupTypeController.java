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
import com.github.markzhl.admin.entity.sys.BaseGroupType;
import com.github.markzhl.admin.service.sys.BaseGroupTypeService;
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
@RequestMapping("/sys/baseGroupType")
public class BaseGroupTypeController extends BaseController<BaseGroupTypeService,BaseGroupType> {
	@RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<BaseGroupType> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
    	Page<BaseGroupType> page = new Page<BaseGroupType>();
    	page.setSize(limit);
    	page.setCurrent(offset);
    	Wrapper<BaseGroupType> wrapper = new EntityWrapper<BaseGroupType>(new BaseGroupType());
    	if(StringUtils.isNotBlank(name)){
    		wrapper.like("name", "%" + name + "%");
    	}
    	page = baseService.selectPage(page, wrapper);
        return new TableResultResponse<BaseGroupType>(page.getRecords().size(),page.getRecords());
    }
}
