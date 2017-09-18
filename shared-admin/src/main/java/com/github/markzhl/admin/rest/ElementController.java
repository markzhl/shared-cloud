package com.github.markzhl.admin.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.service.ElementService;
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
@RequestMapping("element")
public class ElementController extends BaseController<ElementService, Element> {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public TableResultResponse<Element> page(@RequestParam(defaultValue = "10") int limit,
      @RequestParam(defaultValue = "1") int offset,String name, @RequestParam(defaultValue = "0") int menuId) {
    Example example = new Example(Element.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("menuId", menuId);
    if(StringUtils.isNotBlank(name)){
      criteria.andLike("name", "%" + name + "%");
    }
    List<Element> elements = baseService.selectByExample(example);
    return new TableResultResponse<Element>(elements.size(), elements);
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public ObjectRestResponse<Element> getAuthorityElement(String menuId) {
    int userId = userService.getUserByUsername(getCurrentUserName()).getId();
    List<Element> elements = baseService.getAuthorityElementByUserId(userId + "",menuId);
    return new ObjectRestResponse<List<Element>>().rel(true).result(elements);
  }

  @RequestMapping(value = "/user/menu", method = RequestMethod.GET)
  @ResponseBody
  public ObjectRestResponse<Element> getAuthorityElement() {
    int userId = userService.getUserByUsername(getCurrentUserName()).getId();
    List<Element> elements = baseService.getAuthorityElementByUserId(userId + "");
    return new ObjectRestResponse<List<Element>>().rel(true).result(elements);
  }
}
