package com.hjy.demo.shiro.service.impl;


import com.hjy.demo.shiro.dao.UserInfoDao;
import com.hjy.demo.shiro.entity.UserInfo;
import com.hjy.demo.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}