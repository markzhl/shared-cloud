package com.github.markzhl.admin.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.Form;
import com.github.markzhl.admin.service.FormService;
import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.msg.TableResultResponse;
import com.github.markzhl.common.rest.BaseController;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("form")
public class FormController extends BaseController<FormService, Form> {
	@RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Form> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
        Example example = new Example(Form.class);
        if(StringUtils.isNotBlank(name)) {
            example.createCriteria().andLike("formName", "%" + name + "%");
            example.createCriteria().andLike("formDesc", "%" + name + "%");
        }
        int count = baseService.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<Form>(count,baseService.selectByExample(example));
    }
	
    @RequestMapping(value = "/{id}/design", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse formDesign(@PathVariable  int id, String type, String parseForm){
    	baseService.modifyForm(id, type, parseForm);
        return new ObjectRestResponse().rel(true);
    }
}
