package com.ltb.gateway.core.session;

/**
 * 会话网关工厂接口
 *
 * @author leetao
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);
}
