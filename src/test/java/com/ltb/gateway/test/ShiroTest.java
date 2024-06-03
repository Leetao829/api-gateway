package com.ltb.gateway.test;


import com.ltb.gateway.authorization.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ShiroTest {

    private static final Logger logger = LoggerFactory.getLogger(ShiroTest.class);

    @Test
    public void test_jwt(){
        String issuer = "leetao";
        long ttlMillis = 7 * 24 * 60 * 60 * 1000L;
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "leetao");
        String token = JwtUtil.encode(issuer, ttlMillis, claims);
        System.out.println(token);
        Claims parser = JwtUtil.decode(token);
        System.out.println(parser);
    }

    @Test
    public void test_shiro(){
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:test-shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("xiaofuge", "456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("身份验证失败");
        }
        System.out.println(subject.isAuthenticated());
        subject.logout();
    }
}
