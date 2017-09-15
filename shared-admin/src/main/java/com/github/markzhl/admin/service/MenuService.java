package com.github.markzhl.admin.service;

import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Menu;
import com.github.markzhl.admin.mapper.MenuMapper;
import com.github.markzhl.common.service.BaseService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author mark
 * @create 2017-06-12 8:48
 */
@Service
public class MenuService extends BaseService<MenuMapper,Menu> {
    @Override
    public void insertSelective(Menu entity) {
        if(CommonConstant.ROOT == entity.getParentId()){
            entity.setPath("/"+entity.getCode());
        }else{
            Menu parent = this.selectById(entity.getParentId());
            entity.setPath(parent.getPath()+"/"+entity.getCode());
        }
        super.insertSelective(entity);
    }

    @Override
    public void updateById(Menu entity) {
        if(CommonConstant.ROOT == entity.getParentId()){
            entity.setPath("/"+entity.getCode());
        }else{
            Menu parent = this.selectById(entity.getParentId());
            entity.setPath(parent.getPath()+"/"+entity.getCode());
        }
        super.updateById(entity);
    }
    /**
     * 获取用户可以访问的菜单
     * @param id
     * @return
     */
    public List<Menu> getUserAuthorityMenuByUserId(int id){
        return mapper.selectAuthorityMenuByUserId(id);
    }

    /**
     * 根据用户获取可以访问的系统
     * @param id
     * @return
     */
    public List<Menu> getUserAuthoritySystemByUserId(int id){
        return mapper.selectAuthoritySystemByUserId(id);
    }
}
