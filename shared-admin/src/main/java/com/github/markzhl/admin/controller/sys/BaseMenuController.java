package com.github.markzhl.admin.controller.sys;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.sys.BaseMenu;
import com.github.markzhl.admin.service.sys.BaseMenuService;
import com.github.markzhl.admin.service.sys.BaseUserService;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.MenuTree;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.util.TreeUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/baseMenu")
public class BaseMenuController extends BaseController<BaseMenuService,BaseMenu> {
	@Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseMenu> list(String title) {
        Wrapper<BaseMenu> wrapper = new EntityWrapper<BaseMenu>(new BaseMenu());
        if (StringUtils.isNotBlank(title))
        	wrapper.like("title", "%" + title + "%");
        return baseService.selectList(wrapper);
    }

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseMenu> getSystem() {
        Wrapper<BaseMenu> wrapper = new EntityWrapper<BaseMenu>(new BaseMenu());
        wrapper.where("parent_id={0}", CommonConstant.ROOT);
        return baseService.selectList(wrapper);
    }

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> listBaseMenu(Long parentId) {
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<MenuTree>();
        }
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        BaseMenu parent = baseService.selectById(parentId);
        Wrapper<BaseMenu> wrapper = new EntityWrapper<BaseMenu>(new BaseMenu());
        wrapper.like("path", parent.getPath() + "%").and().ne("id",parent.getId());
        return getMenuTree(baseService.selectList(wrapper), parent.getId());
    }

    @RequestMapping(value = "/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<AuthorityMenuTree> listAuthorityBaseMenu() {
        List<AuthorityMenuTree> trees = new ArrayList<AuthorityMenuTree>();
        AuthorityMenuTree node = null;
        for (BaseMenu menu : baseService.selectList(null)) {
            node = new AuthorityMenuTree();
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,CommonConstant.ROOT);
    }

    @RequestMapping(value = "/user/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> listUserAuthorityBaseMenu(Long parentId){
    	Long userId = baseUserService.getUserByUsername(getCurrentUserName()).getId();
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<MenuTree>();
        }
        return getMenuTree(baseService.getUserAuthorityMenuByUserId(userId),parentId);
    }

    @RequestMapping(value = "/user/system", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseMenu> listUserAuthoritySystem() {
    	Long userId = baseUserService.getUserByUsername(getCurrentUserName()).getId();
        return baseService.getUserAuthoritySystemByUserId(userId);
    }

    private List<MenuTree> getMenuTree(List<BaseMenu> menus,Long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (BaseMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }
	
}
