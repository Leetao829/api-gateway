package com.ltb.gateway.core.bind;

import com.ltb.gateway.core.mapping.HttpCommandType;
import com.ltb.gateway.core.session.GatewaySession;
import com.ltb.gateway.core.session.Configuration;

import java.lang.reflect.Method;
import java.util.Map;

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

    public Object execute(GatewaySession session, Map<String,Object> params){
        Object result = null;
        switch (type){
            case GET:
                result = session.get(methodName,params);
                break;
            case POST:
                result = session.post(methodName,params);
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
