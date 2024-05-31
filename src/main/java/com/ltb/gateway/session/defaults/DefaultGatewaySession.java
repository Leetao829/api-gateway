package com.ltb.gateway.session.defaults;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.mapping.HttpStatement;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * 默认网关会话实现类
 *
 * @author leetao
 */
public class DefaultGatewaySession implements GatewaySession {

    private final Configuration configuration;

    public DefaultGatewaySession(Configuration configuration){
        this.configuration = configuration;
    }


    @Override
    public Object get(String uri, Object args) {
        /**
         * 以后这部分内容，在执行器中进行处理
         */
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String application = httpStatement.getApplication();
        String interfaceName = httpStatement.getInterfaceName();
        //获取基础服务
        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);
        ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);
        //构建dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig);
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(referenceConfig);
        return genericService.$invoke(httpStatement.getMethodName(), new String[]{"java.lang.String"},new Object[]{"李涛"});
    }

    @Override
    public IGenericReference getMapper(String uri) {
        return configuration.getMapper(uri,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
