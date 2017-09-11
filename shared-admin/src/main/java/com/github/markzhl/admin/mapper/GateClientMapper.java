package com.github.markzhl.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.markzhl.admin.entity.GateClient;

import tk.mybatis.mapper.common.Mapper;

public interface GateClientMapper extends Mapper<GateClient> {
    public void insertClientServiceById(@Param("clientId") int clientId, @Param("serviceId") int serviceId);
    public void deleteClientServiceById(@Param("clientId") int clientId);
}