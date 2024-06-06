package com.ltb.gateway.core.authorization.auth;

import com.ltb.gateway.core.authorization.GatewayAuthorizingToken;
import com.ltb.gateway.core.authorization.IAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class AuthService implements IAuth {

    private Subject subject;

    public AuthService(){
        //获取SecurityManager工厂，使用shiro.ini配置文件初始化SecurityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject以及Token
        this.subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean validate(String id, String token) {
        try {
            //验证身份
            subject.login(new GatewayAuthorizingToken(id,token));
            return subject.isAuthenticated();
        } finally {
            subject.logout();
        }

    }
}
