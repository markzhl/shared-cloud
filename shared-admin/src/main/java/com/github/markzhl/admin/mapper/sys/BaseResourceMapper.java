package com.github.markzhl.admin.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.BaseResource;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseResourceMapper extends BaseMapper<BaseResource> {
	public List<BaseResource> selectAuthorityResourceByUserId(@Param("userId")String userId);
    public List<BaseResource> selectAuthorityMenuResourceByUserId(@Param("userId")String userId,@Param("menuId")String menuId);
    public List<BaseResource> selectAuthorityResourceByClientId(@Param("clientId")String clientId);
}