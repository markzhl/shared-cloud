package com.github.markzhl.admin.biz;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.entity.GateClient;
import com.github.markzhl.admin.mapper.ElementMapper;
import com.github.markzhl.admin.mapper.GateClientMapper;
import com.github.markzhl.common.biz.BaseBiz;
import com.github.markzhl.common.constant.UserConstant;

/**
 * 
 *
 * @author mark
 * @create 2017-06-29 15:58
 */
@Service
public class GateClientBiz extends BaseBiz<GateClientMapper,GateClient> {
    @Autowired
    private ElementMapper elementMapper;
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

    public List<Element> getClientServices(int id) {
       return elementMapper.selectAuthorityElementByClientId(id+"");
    }
}
