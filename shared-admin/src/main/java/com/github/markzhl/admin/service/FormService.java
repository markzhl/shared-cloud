package com.github.markzhl.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.markzhl.admin.entity.Form;
import com.github.markzhl.admin.mapper.FormMapper;
import com.github.markzhl.common.service.BaseService;
import com.github.markzhl.common.util.JacksonConverter;

@Service
public class FormService extends BaseService<FormMapper, Form> {
    
	public void modifyForm(int id, String type, String parseForm) {
		Form entity = super.selectById(id);
		Map<String, Object> map = JacksonConverter.getInstance().json2Map(parseForm);
		String template = (String)map.get("template");
		String parse = (String)map.get("parse");
		List dataList = (List) map.get("data");
		String data = (String)JacksonConverter.getInstance().toJson(dataList);
		entity.setContent(template);
		entity.setContentParse(parse);
		entity.setContentData(data);
		entity.setFields(Short.valueOf(dataList.size()+""));
		super.updateSelectiveById(entity);
    }
}
