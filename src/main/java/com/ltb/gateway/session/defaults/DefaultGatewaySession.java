package com.ltb.gateway.session.defaults;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.datasource.Connection;
import com.ltb.gateway.datasource.DataSource;
import com.ltb.gateway.mapping.HttpStatement;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;
import com.ltb.gateway.type.SimpleTypeRegistry;

import java.util.Map;

/**
 * 默认网关会话实现类
 *
 * @author leetao
 */
public class DefaultGatewaySession implements GatewaySession {

    private final Configuration configuration;
    private String uri;
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration,String uri,DataSource dataSource){
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }


    @Override
    public Object get(String methodName, Map<String,Object> params) {
        Connection connection = dataSource.getConnection();
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String parameterType = httpStatement.getParameterType();
        return connection.execute(methodName,new String[]{parameterType},new String[]{"ignore"},
                SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params});
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
