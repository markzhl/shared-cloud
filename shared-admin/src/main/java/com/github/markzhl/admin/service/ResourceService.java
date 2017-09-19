package com.github.markzhl.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.markzhl.admin.entity.Resource;
import com.github.markzhl.admin.mapper.ResourceMapper;
import com.github.markzhl.common.service.BaseService;

/**
 * 
 *
 * @author mark
 */
@Service
public class ResourceService extends BaseService<ResourceMapper,Resource> {
    public List<Resource> getAuthorityResourceByUserId(String userId){
       return mapper.selectAuthorityResourceByUserId(userId);
    }
    public List<Resource> getAuthorityResourceByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuResourceByUserId(userId,menuId);
    }
}
