package com.github.markzhl.admin.service.sys;

import com.github.markzhl.admin.entity.sys.BaseGroup;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.GroupUsers;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseGroupService extends IService<BaseGroup> {
	public GroupUsers getGroupUsers(Long groupId);
	public void modifyGroupUsers(Long groupId, String members, String leaders);
	public void modifyAuthorityMenu(Long groupId, List<AuthorityMenuTree> menuTrees);
	public void modifyAuthorityResource(Long groupId,Long menuId,Long resourceId);
	public void removeAuthorityResource(Long groupId, Long menuId, Long resourceId);
	public List<AuthorityMenuTree> getAuthorityMenu(Long groupId);
	public List<Integer> getAuthorityResource(Long groupId);
}
