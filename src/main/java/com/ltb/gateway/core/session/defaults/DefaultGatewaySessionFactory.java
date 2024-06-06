package com.ltb.gateway.core.session.defaults;

import com.ltb.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import com.ltb.gateway.core.executor.Executor;
import com.ltb.gateway.core.datasource.DataSource;
import com.ltb.gateway.core.datasource.DataSourceFactory;
import com.ltb.gateway.core.session.Configuration;
import com.ltb.gateway.core.session.GatewaySession;
import com.ltb.gateway.core.session.GatewaySessionFactory;

/**
 * 默认网关会话工厂
 *
 * @author leetao
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
        //创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        return new DefaultGatewaySession(configuration,uri,executor);
    }
}
