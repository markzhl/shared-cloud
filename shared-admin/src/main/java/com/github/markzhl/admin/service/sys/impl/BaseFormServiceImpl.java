package com.github.markzhl.admin.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.entity.sys.BaseForm;
import com.github.markzhl.admin.mapper.sys.BaseFormMapper;
import com.github.markzhl.admin.service.sys.BaseFormService;
import com.github.markzhl.common.util.EntityUtils;
import com.github.markzhl.common.util.JacksonConverter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class BaseFormServiceImpl extends ServiceImpl<BaseFormMapper, BaseForm> implements BaseFormService {
	public void modifyForm(Long id, String type, String parseForm) {
		BaseForm entity = super.selectById(id);
		Map<String, Object> map = JacksonConverter.getInstance().json2Map(parseForm);
		String template = (String)map.get("template");
		String parse = (String)map.get("parse");
		List dataList = (List) map.get("data");
		String data = (String)JacksonConverter.getInstance().toJson(dataList);
		entity.setContent(template);
		entity.setContentParse(parse);
		entity.setContentData(data);
		entity.setFields(dataList.size());
		EntityUtils.setCreateInfo(entity);
		super.updateById(entity);
    }
}
