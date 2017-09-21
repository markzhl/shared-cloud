package com.github.markzhl.admin.controller.sys;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.markzhl.admin.entity.sys.BaseForm;
import com.github.markzhl.admin.service.sys.BaseFormService;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.msg.ObjectRestResponse;
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
@RequestMapping("/sys/baseForm")
public class BaseFormController extends BaseController<BaseFormService,BaseForm> {
	@RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<BaseForm> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
    	Page<BaseForm> page = new Page<BaseForm>();
    	page.setSize(limit);
    	page.setCurrent(offset);
    	Wrapper<BaseForm> wrapper = new EntityWrapper<BaseForm>(new BaseForm());
    	if(StringUtils.isNotBlank(name)){
    		wrapper.like("form_name", "%" + name + "%").or().
    		like("form_desc", "%" + name + "%");
    	}
    	page = baseService.selectPage(page, wrapper);
        return new TableResultResponse<BaseForm>(page.getRecords().size(),page.getRecords());
    }
	
	@RequestMapping(value = "/{id}/design", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse formDesign(@PathVariable  Long id, String type, String parseForm){
    	baseService.modifyForm(id, type, parseForm);
        return new ObjectRestResponse().rel(true);
    }
}
