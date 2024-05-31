package com.ltb.gateway.session.defaults;

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
    public GatewaySession openSession() {
        return new DefaultGatewaySession(configuration);
    }
}
