package com.github.markzhl.admin.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.github.markzhl.admin.entity.sys.BaseForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseFormService extends IService<BaseForm> {
	public void modifyForm(Long id, String type, String parseForm);
}
