package com.github.markzhl.admin.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.entity.sys.GateClient;
import com.github.markzhl.admin.mapper.sys.BaseResourceMapper;
import com.github.markzhl.admin.mapper.sys.GateClientMapper;
import com.github.markzhl.admin.service.sys.GateClientService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class GateClientServiceImpl extends ServiceImpl<GateClientMapper, GateClient> implements GateClientService {
	@Autowired
    private BaseResourceMapper baseResourceMapper;
	
    public void modifyClientServices(Long id, String services) {
    	baseMapper.deleteById(id);
        if(!StringUtils.isEmpty(services)){
            String[] mem = services.split(",");
            for(String m:mem){
            	baseMapper.insertClientServiceById(id, Long.parseLong(m));
            }
        }
    }
	
	public List<BaseResource> getClientServices(Long id) {
	       return baseResourceMapper.selectAuthorityResourceByClientId(id+"");
	}
	
}
