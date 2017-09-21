package com.github.markzhl.admin.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.BaseUser;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseUserMapper extends BaseMapper<BaseUser> {
	public List<BaseUser> selectMemberByGroupId(@Param("groupId") Long groupId);
    public List<BaseUser> selectLeaderByGroupId(@Param("groupId") Long groupId);
}