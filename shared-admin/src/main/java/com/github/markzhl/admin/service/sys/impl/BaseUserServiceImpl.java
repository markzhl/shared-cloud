package com.github.markzhl.admin.service.sys.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.markzhl.admin.entity.sys.BaseUser;
import com.github.markzhl.admin.mapper.sys.BaseUserMapper;
import com.github.markzhl.admin.service.sys.BaseUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public BaseUser getUserByUsername(String username){
    	BaseUser user = new BaseUser();
        user.setUsername(username);
        return baseMapper.selectOne(user);
    }
}
