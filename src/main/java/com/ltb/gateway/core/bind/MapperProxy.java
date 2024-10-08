package com.ltb.gateway.core.bind;

import com.ltb.gateway.core.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射代理调用
 *
 * @author leetao
 */
public class MapperProxy implements MethodInterceptor {

    private GatewaySession gatewaySession;

    private final String uri;

    public MapperProxy(GatewaySession gatewaySession,String uri){
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MapperMethod mapperMethod = new MapperMethod(uri,method,gatewaySession.getConfiguration());
        return mapperMethod.execute(gatewaySession, (Map<String,Object>)args[0]);
    }
}
