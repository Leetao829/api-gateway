package com.ltb.gateway.session;

import com.ltb.gateway.bind.IGenericReference;

/**
 * 用户处理网关http请求
 *
 * @author leetao
 */
public interface GatewaySession {

    Object get(String methodName,Object parameter);

    IGenericReference getMapper();

    Configuration getConfiguration();
}
