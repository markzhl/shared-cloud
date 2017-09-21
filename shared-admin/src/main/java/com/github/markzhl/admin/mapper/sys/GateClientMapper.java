package com.github.markzhl.admin.mapper.sys;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.markzhl.admin.entity.sys.GateClient;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface GateClientMapper extends BaseMapper<GateClient> {
	public void insertClientServiceById(@Param("clientId") Long clientId, @Param("serviceId") Long serviceId);
    public void deleteClientServiceById(@Param("clientId") Long clientId);
}