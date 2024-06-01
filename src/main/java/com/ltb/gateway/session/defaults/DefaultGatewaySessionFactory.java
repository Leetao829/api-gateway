package com.ltb.gateway.session.defaults;

import com.ltb.gateway.datasource.DataSource;
import com.ltb.gateway.datasource.DataSourceFactory;
import com.ltb.gateway.datasource.unpooled.UnpooledDataSourceFactory;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;
import com.ltb.gateway.session.GatewaySessionFactory;

/**
 * 默认网关会话工厂
 *
 * @author leetao
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration,uri);
        DataSource dataSource = dataSourceFactory.getDataSource();
        return new DefaultGatewaySession(configuration,uri,dataSource);
    }
}
