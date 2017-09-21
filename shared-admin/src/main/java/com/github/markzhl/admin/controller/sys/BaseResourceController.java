package com.github.markzhl.admin.controller.sys;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.service.sys.BaseResourceService;
import com.github.markzhl.admin.service.sys.BaseUserService;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.msg.TableResultResponse;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/baseResource")
public class BaseResourceController extends BaseController<BaseResourceService,BaseResource> {
	  @Autowired
	  private BaseUserService baseUserService;

	  @RequestMapping(value = "/page", method = RequestMethod.GET)
	  @ResponseBody
	  public TableResultResponse<BaseResource> page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,String name, @RequestParam(defaultValue = "0") int menuId) {
	    Wrapper<BaseResource> wrapper = new EntityWrapper<BaseResource>(new BaseResource());
	    wrapper.where("menu_id={0}", menuId);
	    
	    if(StringUtils.isNotBlank(name)){
	    	wrapper.and().like("name", "%" + name + "%");
	    }
	    List<BaseResource> resources = baseService.selectList(wrapper);
	    return new TableResultResponse<BaseResource>(resources.size(), resources);
	  }

	  @RequestMapping(value = "/user", method = RequestMethod.GET)
	  @ResponseBody
	  public ObjectRestResponse<BaseResource> getAuthorityResource(String menuId) {
		Long userId = baseUserService.getUserByUsername(getCurrentUserName()).getId();
	    List<BaseResource> resources = baseService.getAuthorityResourceByUserId(userId + "",menuId);
	    return new ObjectRestResponse<List<BaseResource>>().rel(true).result(resources);
	  }

	  @RequestMapping(value = "/user/menu", method = RequestMethod.GET)
	  @ResponseBody
	  public ObjectRestResponse<BaseResource> getAuthorityResource() {
		Long userId = baseUserService.getUserByUsername(getCurrentUserName()).getId();
	    List<BaseResource> resources = baseService.getAuthorityResourceByUserId(userId + "");
	    return new ObjectRestResponse<List<BaseResource>>().rel(true).result(resources);
	  }
}
