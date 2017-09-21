package com.github.markzhl.admin.mapper.sys;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.BaseResourceAuthority;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseResourceAuthorityMapper extends BaseMapper<BaseResourceAuthority> {
	public void deleteByAuthorityIdAndResourceType(@Param("authorityId") String authorityId,@Param("resourceType") String resourceType);
}