package com.ltb.gateway.bind;

import com.ltb.gateway.mapping.HttpStatement;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private Configuration configuration;

    private final Map<String,MapperProxyFactory> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration){
        this.configuration = configuration;
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession){
        MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        if(null == mapperProxyFactory){
            throw new RuntimeException(uri + " is not known to MapperRegistry");
        }
        return mapperProxyFactory.newInstance(gatewaySession);
    }

    public void addMapper(HttpStatement httpStatement){
        String uri = httpStatement.getUri();
        if(hasMapper(uri)){
            throw new RuntimeException(uri + " is already known to MapperRegistry");
        }
        knownMappers.put(uri,new MapperProxyFactory(uri));
        configuration.addHttpStatement(httpStatement);
    }

    public <T> boolean hasMapper(String uri){
        return knownMappers.containsKey(uri);
    }
}
