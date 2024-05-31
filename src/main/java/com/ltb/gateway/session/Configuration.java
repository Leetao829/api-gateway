package com.ltb.gateway.session;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.bind.MapperRegistry;
import com.ltb.gateway.mapping.HttpStatement;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话生命周期配置项
 *
 * @author leetao
 */
public class Configuration {

    private final MapperRegistry mapperRegistry = new MapperRegistry(this);

    private final Map<String, HttpStatement> httpStatements = new HashMap<>();

    //Rpc 应用服务配置项
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    //Rpc注册中心配置项
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    //Rpc 泛化服务配置项
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public Configuration(){
        //TODO 后期从配置项中获取
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.ltb.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        applicationConfigMap.put("api-gateway-test",application);
        registryConfigMap.put("api-gateway-test",registry);
        referenceConfigMap.put("com.ltb.gateway.rpc.IActivityBooth",reference);
    }

    public ApplicationConfig getApplicationConfig(String applicationName){
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName){
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName){
        return referenceConfigMap.get(interfaceName);
    }

    public void addMapper(HttpStatement httpStatement){
        mapperRegistry.addMapper(httpStatement);
    }

    public IGenericReference getMapper(String uri,GatewaySession gatewaySession){
        return mapperRegistry.getMapper(uri,gatewaySession);
    }

    public void addHttpStatement(HttpStatement httpStatement){
        httpStatements.put(httpStatement.getUri(),httpStatement);
    }

    public HttpStatement getHttpStatement(String uri){
        return httpStatements.get(uri);
    }
}
