package com.github.markzhl.admin.controller.sys;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.markzhl.admin.entity.sys.BaseGroup;
import com.github.markzhl.admin.service.sys.BaseGroupService;
import com.github.markzhl.admin.service.sys.BaseResourceAuthorityService;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.GroupUsers;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.msg.ObjectRestResponse;

import io.swagger.annotations.Api;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/baseGroup")
@Api("群组模块")
public class BaseGroupController extends BaseController<BaseGroupService,BaseGroup> {
	@Autowired
    private BaseResourceAuthorityService baseResourceAuthorityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseGroup> list(String name,String groupType) {
        if(StringUtils.isBlank(name)&&StringUtils.isBlank(groupType))
            return new ArrayList<BaseGroup>();
        Wrapper<BaseGroup> wrapper = new EntityWrapper<BaseGroup>(new BaseGroup());
        if (StringUtils.isNotBlank(name))
        	wrapper.like("name", "%" + name + "%");
        if (StringUtils.isNotBlank(groupType))
        	wrapper.andNew("group_type={0}", groupType);
        
        return baseService.selectList(wrapper);
    }

    @RequestMapping(value = "/{id}/user", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifiyUsers(@PathVariable Long id,String members,String leaders){
        baseService.modifyGroupUsers(id, members, leaders);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<GroupUsers> getUsers(@PathVariable Long id){
        return new ObjectRestResponse<GroupUsers>().rel(true).result(baseService.getGroupUsers(id));
    }

    @RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse modifiyMenuAuthority(@PathVariable  Long id, String menuTrees){
        List<AuthorityMenuTree> menus =  JSONObject.parseArray(menuTrees,AuthorityMenuTree.class);
        baseService.modifyAuthorityMenu(id, menus);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<AuthorityMenuTree>> getMenuAuthority(@PathVariable  Long id){
        return new ObjectRestResponse().result(baseService.getAuthorityMenu(id)).rel(true);
    }

    @RequestMapping(value = "/{id}/authority/resource/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addResourceAuthority(@PathVariable  Long id, Long menuId, Long resourceId){
        baseService.modifyAuthorityResource(id,menuId,resourceId);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/resource/remove", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse removeResourceAuthority(@PathVariable Long id, Long menuId, Long resourceId){
        baseService.removeAuthorityResource(id,menuId,resourceId);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/resource", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<Integer>> addResourceAuthority(@PathVariable  Long id){
        return new ObjectRestResponse().result(baseService.getAuthorityResource(id)).rel(true);
    }
}
