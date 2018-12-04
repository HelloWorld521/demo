package com.hjy.demo.shiro.service;

import com.hjy.demo.shiro.entity.UserInfo;

public interface UserInfoService {
    /**
     * 通过username查找用户信息;
     */
    public UserInfo findByUsername(String username);
}