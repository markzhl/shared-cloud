package com.github.markzhl.admin.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.Resource;
import com.github.markzhl.admin.service.ResourceService;
import com.github.markzhl.admin.service.UserService;
import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.msg.TableResultResponse;
import com.github.markzhl.common.rest.BaseController;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController<ResourceService, Resource> {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public TableResultResponse<Resource> page(@RequestParam(defaultValue = "10") int limit,
      @RequestParam(defaultValue = "1") int offset,String name, @RequestParam(defaultValue = "0") int menuId) {
    Example example = new Example(Resource.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("menuId", menuId);
    if(StringUtils.isNotBlank(name)){
      criteria.andLike("name", "%" + name + "%");
    }
    List<Resource> resources = baseService.selectByExample(example);
    return new TableResultResponse<Resource>(resources.size(), resources);
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public ObjectRestResponse<Resource> getAuthorityResource(String menuId) {
    int userId = userService.getUserByUsername(getCurrentUserName()).getId();
    List<Resource> resources = baseService.getAuthorityResourceByUserId(userId + "",menuId);
    return new ObjectRestResponse<List<Resource>>().rel(true).result(resources);
  }

  @RequestMapping(value = "/user/menu", method = RequestMethod.GET)
  @ResponseBody
  public ObjectRestResponse<Resource> getAuthorityResource() {
    int userId = userService.getUserByUsername(getCurrentUserName()).getId();
    List<Resource> resources = baseService.getAuthorityResourceByUserId(userId + "");
    return new ObjectRestResponse<List<Resource>>().rel(true).result(resources);
  }
}
