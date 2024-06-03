package com.ltb.gateway.session.defaults;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.executor.Executor;
import com.ltb.gateway.mapping.HttpStatement;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;
import java.util.Map;

/**
 * 默认网关会话实现类
 *
 * @author leetao
 */
public class DefaultGatewaySession implements GatewaySession {

    private final Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration,String uri,Executor executor){
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }


    @Override
    public Object get(String methodName, Map<String,Object> params) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement,params);
        } catch (Exception e) {
            throw new RuntimeException("Error exec get. Cause:"+e);
        }
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName,params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
