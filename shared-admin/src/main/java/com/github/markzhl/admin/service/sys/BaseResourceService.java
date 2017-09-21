package com.github.markzhl.admin.service.sys;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.github.markzhl.admin.entity.sys.BaseResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseResourceService extends IService<BaseResource> {
	public List<BaseResource> getAuthorityResourceByUserId(String userId);
	public List<BaseResource> getAuthorityResourceByUserId(String userId,String menuId);
}
