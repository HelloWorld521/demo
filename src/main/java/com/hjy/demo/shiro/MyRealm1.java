package com.hjy.demo.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 自定义 Realm 实现
 *
 * @author hjy
 * @date 2018/11/26
 **/
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myRealm1";
    }

    /**
     * token 判断是否支持
     *
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        // 仅支持 UsernamePasswordToken 类型的 Toke
        return authenticationToken instanceof UsernamePasswordToken;
    }

    /**
     * 根据 Token 获取认证信息
     *
     * @param authenticationToken token
     * @return AuthenticationInfo 实现
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        String password = String.valueOf((char[]) authenticationToken.getCredentials());
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException(); // 用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); // 密码错误
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
