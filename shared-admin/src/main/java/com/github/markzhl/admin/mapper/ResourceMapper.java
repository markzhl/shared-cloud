package com.github.markzhl.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.markzhl.admin.entity.Resource;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {
    public List<Resource> selectAuthorityResourceByUserId(@Param("userId")String userId);
    public List<Resource> selectAuthorityMenuResourceByUserId(@Param("userId")String userId,@Param("menuId")String menuId);
    public List<Resource> selectAuthorityResourceByClientId(@Param("clientId")String clientId);

}