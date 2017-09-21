package com.github.markzhl.admin.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.markzhl.admin.entity.sys.BaseResourceAuthority;
import com.github.markzhl.admin.service.sys.BaseResourceAuthorityService;
import com.github.markzhl.common.base.BaseController;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/baseResourceAuthority")
public class BaseResourceAuthorityController extends BaseController<BaseResourceAuthorityService,BaseResourceAuthority> {
	
}
