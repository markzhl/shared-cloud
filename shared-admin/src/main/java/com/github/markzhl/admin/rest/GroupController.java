package com.github.markzhl.admin.rest;

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
import com.github.markzhl.admin.entity.Group;
import com.github.markzhl.admin.service.GroupService;
import com.github.markzhl.admin.service.ResourceAuthorityService;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.GroupUsers;
import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.rest.BaseController;

import io.swagger.annotations.Api;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("group")
@Api("群组模块")
public class GroupController extends BaseController<GroupService, Group> {
    @Autowired
    private ResourceAuthorityService resourceAuthorityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Group> list(String name,String groupType) {
        if(StringUtils.isBlank(name)&&StringUtils.isBlank(groupType))
            return new ArrayList<Group>();
        Example example = new Example(Group.class);
        if (StringUtils.isNotBlank(name))
            example.createCriteria().andLike("name", "%" + name + "%");
        if (StringUtils.isNotBlank(groupType))
            example.createCriteria().andEqualTo("groupType", groupType);

        return baseService.selectByExample(example);
    }

    @RequestMapping(value = "/{id}/user", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifiyUsers(@PathVariable int id,String members,String leaders){
        baseService.modifyGroupUsers(id, members, leaders);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<GroupUsers> getUsers(@PathVariable int id){
        return new ObjectRestResponse<GroupUsers>().rel(true).result(baseService.getGroupUsers(id));
    }

    @RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse modifiyMenuAuthority(@PathVariable  int id, String menuTrees){
        List<AuthorityMenuTree> menus =  JSONObject.parseArray(menuTrees,AuthorityMenuTree.class);
        baseService.modifyAuthorityMenu(id, menus);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<AuthorityMenuTree>> getMenuAuthority(@PathVariable  int id){
        return new ObjectRestResponse().result(baseService.getAuthorityMenu(id)).rel(true);
    }

    @RequestMapping(value = "/{id}/authority/element/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addElementAuthority(@PathVariable  int id,int menuId, int elementId){
        baseService.modifyAuthorityElement(id,menuId,elementId);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/element/remove", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse removeElementAuthority(@PathVariable int id,int menuId, int elementId){
        baseService.removeAuthorityElement(id,menuId,elementId);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/authority/element", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<Integer>> addElementAuthority(@PathVariable  int id){
        return new ObjectRestResponse().result(baseService.getAuthorityElement(id)).rel(true);
    }

}
