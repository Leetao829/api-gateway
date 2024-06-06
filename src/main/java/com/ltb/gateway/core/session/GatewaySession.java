package com.ltb.gateway.core.session;

import com.ltb.gateway.core.bind.IGenericReference;

import java.util.Map;

/**
 * 用户处理网关http请求
 *
 * @author leetao
 */
public interface GatewaySession {

    Object get(String methodName,Map<String,Object> params);

    Object post(String methodName, Map<String,Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();
}
