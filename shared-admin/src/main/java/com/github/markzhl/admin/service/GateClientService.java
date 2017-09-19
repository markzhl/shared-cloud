package com.github.markzhl.admin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Resource;
import com.github.markzhl.admin.entity.GateClient;
import com.github.markzhl.admin.mapper.ResourceMapper;
import com.github.markzhl.admin.mapper.GateClientMapper;
import com.github.markzhl.common.constant.UserConstant;
import com.github.markzhl.common.service.BaseService;

/**
 * 
 *
 * @author mark
 */
@Service
public class GateClientService extends BaseService<GateClientMapper,GateClient> {
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public void insertSelective(GateClient entity) {
        String secret = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getSecret());
        entity.setSecret(secret);
        entity.setLocked(CommonConstant.BOOLEAN_NUMBER_FALSE);
        super.insertSelective(entity);
    }

    @Override
    public void updateById(GateClient entity) {
        GateClient old =  new GateClient();
        old.setId(entity.getId());
        old = mapper.selectOne(old);
        if(!entity.getSecret().equals(old.getSecret())){
            String secret = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getSecret());
            entity.setSecret(secret);
        }
        super.updateById(entity);
    }

    public void modifyClientServices(int id, String services) {
        mapper.deleteClientServiceById(id);
        if(!StringUtils.isEmpty(services)){
            String[] mem = services.split(",");
            for(String m:mem){
                mapper.insertClientServiceById(id, Integer.parseInt(m));
            }
        }
    }

    public List<Resource> getClientServices(int id) {
       return resourceMapper.selectAuthorityResourceByClientId(id+"");
    }
}
