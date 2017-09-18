package com.github.markzhl.admin.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Menu;
import com.github.markzhl.admin.service.MenuService;
import com.github.markzhl.admin.service.UserService;
import com.github.markzhl.admin.vo.AuthorityMenuTree;
import com.github.markzhl.admin.vo.MenuTree;
import com.github.markzhl.common.rest.BaseController;
import com.github.markzhl.common.util.TreeUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController<MenuService, Menu> {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> list(String title) {
        Example example = new Example(Menu.class);
        if (StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        return baseService.selectByExample(example);
    }

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getSystem() {
        Menu menu = new Menu();
        menu.setParentId(CommonConstant.ROOT);
        return baseService.selectList(menu);
    }

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> listMenu(Integer parentId) {
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<MenuTree>();
        }
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        Example example = new Example(Menu.class);
        Menu parent = baseService.selectById(parentId);
        example.createCriteria().andLike("path", parent.getPath() + "%").andNotEqualTo("id",parent.getId());
        return getMenuTree(baseService.selectByExample(example), parent.getId());
    }

    @RequestMapping(value = "/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<AuthorityMenuTree> listAuthorityMenu() {
        List<AuthorityMenuTree> trees = new ArrayList<AuthorityMenuTree>();
        AuthorityMenuTree node = null;
        for (Menu menu : baseService.selectListAll()) {
            node = new AuthorityMenuTree();
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,CommonConstant.ROOT);
    }

    @RequestMapping(value = "/user/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> listUserAuthorityMenu(Integer parentId){
        int userId = userService.getUserByUsername(getCurrentUserName()).getId();
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
    public List<Menu> listUserAuthoritySystem() {
        int userId = userService.getUserByUsername(getCurrentUserName()).getId();
        return baseService.getUserAuthoritySystemByUserId(userId);
    }

    private List<MenuTree> getMenuTree(List<Menu> menus,int root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }


}
