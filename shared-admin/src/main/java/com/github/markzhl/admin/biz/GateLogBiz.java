package com.github.markzhl.admin.biz;

import com.github.markzhl.admin.entity.GateLog;
import com.github.markzhl.admin.mapper.GateLogMapper;
import com.github.markzhl.common.biz.BaseBiz;

import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author mark
 * @create 2017-07-01 14:36
 */
@Service
public class GateLogBiz extends BaseBiz<GateLogMapper,GateLog> {

    @Override
    public void insert(GateLog entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(GateLog entity) {
        mapper.insertSelective(entity);
    }
}
