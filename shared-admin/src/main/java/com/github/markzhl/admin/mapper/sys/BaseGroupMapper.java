package com.github.markzhl.admin.mapper.sys;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.BaseGroup;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface BaseGroupMapper extends BaseMapper<BaseGroup> {
    public void deleteGroupMembersById (@Param("groupId") Long groupId);
    public void deleteGroupLeadersById (@Param("groupId") Long groupId);
    public void insertGroupMembersById (@Param("groupId") Long groupId,@Param("userId") Long userId);
    public void insertGroupLeadersById (@Param("groupId") Long groupId,@Param("userId") Long userId);
}