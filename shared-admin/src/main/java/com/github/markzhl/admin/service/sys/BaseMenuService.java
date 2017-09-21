package com.github.markzhl.admin.service.sys;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.github.markzhl.admin.entity.sys.BaseMenu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseMenuService extends IService<BaseMenu> {
	public List<BaseMenu> getUserAuthorityMenuByUserId(Long id);
	public List<BaseMenu> getUserAuthoritySystemByUserId(Long id);
}
