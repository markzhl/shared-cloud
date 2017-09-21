package com.github.markzhl.admin.service.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.sys.BaseGroup;
import com.github.markzhl.admin.entity.sys.BaseMenu;
import com.github.markzhl.admin.entity.sys.BaseResourceAuthority;
import com.github.markzhl.admin.mapper.sys.BaseGroupMapper;
import com.github.markzhl.admin.mapper.sys.BaseMenuMapper;
import com.github.markzhl.admin.mapper.sys.BaseResourceAuthorityMapper;
import com.github.markzhl.admin.mapper.sys.BaseUserMapper;
import com.github.markzhl.admin.service.sys.BaseGroupService;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.GroupUsers;
import com.github.markzhl.common.util.EntityUtils;
import com.google.common.collect.Maps;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class BaseGroupServiceImpl extends ServiceImpl<BaseGroupMapper, BaseGroup> implements BaseGroupService {
	@Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private BaseResourceAuthorityMapper baseResourceAuthorityMapper;
    @Autowired
    private BaseMenuMapper baseMenuMapper;
	/**
     * 获取群组关联用户
     * @param groupId
     * @return
     */
    public GroupUsers getGroupUsers(Long groupId) {
        return new GroupUsers(baseUserMapper.selectMemberByGroupId(groupId),baseUserMapper.selectLeaderByGroupId(groupId));
    }

    /**
     * 变更群主所分配用户
     * @param groupId
     * @param members
     * @param leaders
     */
    public void modifyGroupUsers(Long groupId, String members, String leaders){
        baseMapper.deleteGroupLeadersById(groupId);
        baseMapper.deleteGroupMembersById(groupId);
        if(!StringUtils.isEmpty(members)){
            String[] mem = members.split(",");
            for(String m:mem){
            	baseMapper.insertGroupMembersById(groupId,Long.parseLong(m));
            }
        }
        if(!StringUtils.isEmpty(leaders)){
            String[] mem = leaders.split(",");
            for(String m:mem){
            	baseMapper.insertGroupLeadersById(groupId,Long.parseLong(m));
            }
        }
    }

    /**
     * 变更群组关联的菜单
     * @param groupId
     * @param menuTrees
     */
    public void modifyAuthorityMenu(Long groupId, List<AuthorityMenuTree> menuTrees){
        baseResourceAuthorityMapper.deleteByAuthorityIdAndResourceType(groupId+"",CommonConstant.RESOURCE_TYPE_MENU);
        BaseResourceAuthority entity = null;
        for(AuthorityMenuTree menuTree:menuTrees){
        	entity = new BaseResourceAuthority(CommonConstant.AUTHORITY_TYPE_GROUP,CommonConstant.RESOURCE_TYPE_MENU);
        	entity.setAuthorityId(groupId+"");
        	entity.setResourceId(menuTree.getId()+"");
        	entity.setParentId("-1");
            EntityUtils.setCreateInfo(entity);
            baseResourceAuthorityMapper.insert(entity);
        }
    }

    /**
     * 分配资源权限
     * @param groupId
     * @param menuId
     * @param resourceId
     */
    public void modifyAuthorityResource(Long groupId,Long menuId,Long resourceId){
        BaseResourceAuthority entity = new BaseResourceAuthority(CommonConstant.AUTHORITY_TYPE_GROUP,CommonConstant.RESOURCE_TYPE_BTN);
        entity.setAuthorityId(groupId + "");
        entity.setResourceId(resourceId + "");
        entity.setParentId("-1");
        EntityUtils.setCreateInfo(entity);
        baseResourceAuthorityMapper.insert(entity);
    }

    /**
     * 移除资源权限
     * @param groupId
     * @param menuId
     * @param resourceId
     */
    public void removeAuthorityResource(Long groupId, Long menuId, Long resourceId) {
    	Map<String, Object> columnMap = Maps.newHashMap();
    	columnMap.put("authorityId", groupId);
    	columnMap.put("resourceId", resourceId);
    	columnMap.put("parentId", "-1");
    	baseResourceAuthorityMapper.deleteByMap(columnMap);
    }


    /**
     * 获取群主关联的菜单
     * @param groupId
     * @return
     */
    public List<AuthorityMenuTree> getAuthorityMenu(Long groupId){
        List<BaseMenu> menus = baseMenuMapper.selectMenuByAuthorityId(String.valueOf(groupId), CommonConstant.AUTHORITY_TYPE_GROUP);
        List<AuthorityMenuTree> trees = new ArrayList<AuthorityMenuTree>();
        AuthorityMenuTree node = null;
        for (BaseMenu menu : menus) {
            node = new AuthorityMenuTree();
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return trees;
    }


    public List<Integer> getAuthorityResource(Long groupId) {
        Wrapper<BaseResourceAuthority> wrapper = new EntityWrapper<BaseResourceAuthority>(new BaseResourceAuthority());
        wrapper.where("authorityType", CommonConstant.AUTHORITY_TYPE_GROUP)
        	.and().eq("resourceType", CommonConstant.RESOURCE_TYPE_BTN)
        	.and().eq("authorityId", groupId);
        
        List<BaseResourceAuthority> authorities = baseResourceAuthorityMapper.selectList(wrapper);
        List<Integer> ids = new ArrayList<Integer>();
        for(BaseResourceAuthority auth:authorities){
            ids.add(Integer.parseInt(auth.getResourceId()));
        }
        return ids;
    }
}
