package com.github.markzhl.admin.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.BaseMenu;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseMenuMapper extends BaseMapper<BaseMenu> {
	public List<BaseMenu> selectMenuByAuthorityId(@Param("authorityId") String authorityId,@Param("authorityType") String authorityType);

    /**
     * 根据用户和组的权限关系查找用户可访问菜单
     * @param userId
     * @return
     */
    public List<BaseMenu> selectAuthorityMenuByUserId (@Param("userId") Long userId);

    /**
     * 根据用户和组的权限关系查找用户可访问的系统
     * @param userId
     * @return
     */
    public List<BaseMenu> selectAuthoritySystemByUserId (@Param("userId") Long userId);
}