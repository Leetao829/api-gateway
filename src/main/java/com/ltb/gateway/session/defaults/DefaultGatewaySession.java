package com.ltb.gateway.session.defaults;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.datasource.Connection;
import com.ltb.gateway.datasource.DataSource;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;

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
    public Object get(String methodName, Object parameter) {
        Connection connection = dataSource.getConnection();
        return connection.execute(methodName,new String[]{"java.lang.String"},new String[]{"name"},new Object[]{parameter});
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
