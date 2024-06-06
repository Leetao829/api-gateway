package com.ltb.gateway.test;


import com.ltb.gateway.core.authorization.IAuth;
import com.ltb.gateway.core.authorization.JwtUtil;
import com.ltb.gateway.core.authorization.auth.AuthService;
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
    public void test_auth_service(){
        IAuth auth = new AuthService();
        boolean validate = auth.validate("leetao", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZWV0YW8iLCJleHAiOjE3MTc5OTMxMDAsImlhdCI6MTcxNzM4ODMwMCwia2V5IjoibGVldGFvIn0.DJ7HJ9Btf8sZPBV-p806bhx7RIkOcwxeT3Wtz88lHQg");
        System.out.println(validate ? "验证成功" : "验证失败");
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
