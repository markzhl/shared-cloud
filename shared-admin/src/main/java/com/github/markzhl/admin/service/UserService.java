package com.github.markzhl.admin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.markzhl.admin.entity.User;
import com.github.markzhl.admin.mapper.UserMapper;
import com.github.markzhl.common.constant.UserConstant;
import com.github.markzhl.common.service.BaseService;

/**
 * 
 *
 * @author mark
 */
@Service
public class UserService extends BaseService<UserMapper,User> {

    @Override
    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    public void updateSelectiveById(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.updateById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    /**
     * 获取spring security中的实际用户ID
     * @param securityContextImpl
     * @return
     */
/*    public int getSecurityUserId(SecurityContextImpl securityContextImpl) {
        org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) securityContextImpl.getAuthentication().getPrincipal();
        return this.getUserByUsername(securityUser.getUsername()).getId();
    }*/
}