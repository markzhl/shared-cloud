package com.github.markzhl.admin.service;

import com.github.markzhl.admin.entity.GateLog;
import com.github.markzhl.admin.mapper.GateLogMapper;
import com.github.markzhl.common.service.BaseService;

import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author mark
 */
@Service
public class GateLogService extends BaseService<GateLogMapper,GateLog> {

    @Override
    public void insert(GateLog entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(GateLog entity) {
        mapper.insertSelective(entity);
    }
}
