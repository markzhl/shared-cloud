package com.github.markzhl.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.mapper.ElementMapper;
import com.github.markzhl.common.service.BaseService;

/**
 * 
 *
 * @author mark
 */
@Service
public class ElementService extends BaseService<ElementMapper,Element> {
    public List<Element> getAuthorityElementByUserId(String userId){
       return mapper.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);
    }
}
