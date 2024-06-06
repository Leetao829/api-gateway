package com.ltb.gateway.core.datasource;

import com.ltb.gateway.core.session.Configuration;

/**
 * 数据源工厂
 *
 * @author leetao
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();
}
