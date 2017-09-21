package com.github.markzhl.admin.service.sys.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.entity.sys.BaseMenu;
import com.github.markzhl.admin.mapper.sys.BaseMenuMapper;
import com.github.markzhl.admin.service.sys.BaseMenuService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class BaseMenuServiceImpl extends ServiceImpl<BaseMenuMapper, BaseMenu> implements BaseMenuService {
    /**
     * 获取用户可以访问的菜单
     * @param id
     * @return
     */
    public List<BaseMenu> getUserAuthorityMenuByUserId(Long id){
        return baseMapper.selectAuthorityMenuByUserId(id);
    }

    /**
     * 根据用户获取可以访问的系统
     * @param id
     * @return
     */
    public List<BaseMenu> getUserAuthoritySystemByUserId(Long id){
        return baseMapper.selectAuthoritySystemByUserId(id);
    }
}
