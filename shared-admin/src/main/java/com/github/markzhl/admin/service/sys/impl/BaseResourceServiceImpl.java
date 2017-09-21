package com.github.markzhl.admin.service.sys.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.mapper.sys.BaseResourceMapper;
import com.github.markzhl.admin.service.sys.BaseResourceService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class BaseResourceServiceImpl extends ServiceImpl<BaseResourceMapper, BaseResource> implements BaseResourceService {
	public List<BaseResource> getAuthorityResourceByUserId(String userId){
       return baseMapper.selectAuthorityResourceByUserId(userId);
    }
    public List<BaseResource> getAuthorityResourceByUserId(String userId,String menuId){
        return baseMapper.selectAuthorityMenuResourceByUserId(userId,menuId);
    }
}
