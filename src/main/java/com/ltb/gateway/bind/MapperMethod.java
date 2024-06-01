package com.ltb.gateway.bind;

import com.ltb.gateway.mapping.HttpCommandType;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;

import java.lang.reflect.Method;

/**
 * 绑定调用方法
 *
 * @author leetao
 */
public class MapperMethod {

    private String methodName;

    private final HttpCommandType type;

    public MapperMethod(String uri, Method method, Configuration configuration){
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.type = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session,Object args){
        Object result = null;
        switch (type){
            case GET:
                result = session.get(methodName,args);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("unknown method");
        }
        return result;
    }
}
