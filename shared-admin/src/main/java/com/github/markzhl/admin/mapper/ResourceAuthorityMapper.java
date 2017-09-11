package com.github.markzhl.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.markzhl.admin.entity.ResourceAuthority;

import tk.mybatis.mapper.common.Mapper;

public interface ResourceAuthorityMapper extends Mapper<ResourceAuthority> {
    public void deleteByAuthorityIdAndResourceType(@Param("authorityId")String authorityId,@Param("resourceType") String resourceType);
}