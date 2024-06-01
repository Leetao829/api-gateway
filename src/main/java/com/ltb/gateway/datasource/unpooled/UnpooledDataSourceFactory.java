package com.ltb.gateway.datasource.unpooled;

import com.ltb.gateway.datasource.DataSource;
import com.ltb.gateway.datasource.DataSourceFactory;
import com.ltb.gateway.datasource.DataSourceType;
import com.ltb.gateway.session.Configuration;

/**
 * 数据源工厂
 *
 * @author leetao
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory(){
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
