package com.hjy.demo.shiro.dao;

import com.hjy.demo.shiro.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * example
 *
 * @author hjy
 * @date 2018/12/4
 **/
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    /**
     * 通过 username 查找用户信息
     *
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

}
