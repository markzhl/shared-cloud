package com.github.markzhl.admin.biz;

import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.mapper.ElementMapper;
import com.github.markzhl.common.biz.BaseBiz;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author mark
 * @create 2017-06-23 20:27
 */
@Service
public class ElementBiz extends BaseBiz<ElementMapper,Element> {
    public List<Element> getAuthorityElementByUserId(String userId){
       return mapper.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);
    }
}
