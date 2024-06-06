package com.ltb.gateway.core.datasource.unpooled;

import com.ltb.gateway.core.datasource.Connection;
import com.ltb.gateway.core.datasource.DataSource;
import com.ltb.gateway.core.datasource.DataSourceType;
import com.ltb.gateway.core.datasource.connention.DubboConnection;
import com.ltb.gateway.core.mapping.HttpStatement;
import com.ltb.gateway.core.session.Configuration;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * 无池化数据源
 */
public class UnpooledDataSource implements DataSource {

    private Configuration configuration;
    private HttpStatement httpStatement;
    private DataSourceType dataSourceType;


    @Override
    public Connection getConnection() {
        switch (dataSourceType){
            case HTTP:
                break;
            case Dubbo:
                String application = httpStatement.getApplication();
                String interfaceName = httpStatement.getInterfaceName();
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
                RegistryConfig registryConfig = configuration.getRegistryConfig(application);
                ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);
                return new DubboConnection(applicationConfig,registryConfig,referenceConfig);
            default:
                break;
        }
        throw new RuntimeException("DataSourceType:"+dataSourceType+"没有对应数据源");
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public void setHttpStatement(HttpStatement httpStatement) {
        this.httpStatement = httpStatement;
    }
}
