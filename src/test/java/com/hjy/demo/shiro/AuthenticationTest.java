package com.hjy.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * shiro 身份认证
 *
 * @author hjy
 * @date 2018/11/19
 **/
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("admin", "123456");
    }

    /**
     * 简单 simpleAccountRealm 认证
     */
    @Test
    public void testAuthenticaton() {

        // 1. 构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());

    }

    /**
     * Ini 配置文件认证
     * 步骤: 1. 获取 SecurityManager 并绑定 SecurityUtils
     * 2. 获取主体 token 并登录
     */
    @Test
    public void testInitRealm() {
        // 1. 使用Ini配置文件初始化 SecurityManager
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2 获取 SecurityManager 绑定到 SecurityUtils
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        // 3. 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 4. 登录
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        // 5. 退出
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }

    @Test
    public void testJDBCRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertTrue(subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }


}