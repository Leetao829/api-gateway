package com.ltb.gateway.authorization;

/**
 * 认证服务接口
 *
 * @author leetao
 */
public interface IAuth {

    boolean validate(String id,String token);
}
