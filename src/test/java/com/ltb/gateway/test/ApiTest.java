package com.ltb.gateway.test;

import com.ltb.gateway.core.mapping.HttpCommandType;
import com.ltb.gateway.core.mapping.HttpStatement;
import com.ltb.gateway.core.session.Configuration;
import com.ltb.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import com.ltb.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_gateway() throws InterruptedException, ExecutionException {
        // 1. 创建配置信息加载注册
        Configuration configuration = new Configuration();
        configuration.registryConfig("api-gateway-test","zookeeper://127.0.0.1:2181","com.ltb.gateway.rpc.IActivityBooth","1.0.0");
        configuration.setPort(7397);
        configuration.setHostName("127.0.0.1");

        HttpStatement httpStatement01 = new HttpStatement(
                "api-gateway-test",
                "com.ltb.gateway.rpc.IActivityBooth",
                "sayHi",
                "java.lang.String",
                "/wg/activity/sayHi",
                HttpCommandType.GET,
                false);

        HttpStatement httpStatement02 = new HttpStatement(
                "api-gateway-test",
                "com.ltb.gateway.rpc.IActivityBooth",
                "insert",
                "com.ltb.gateway.rpc.dto.XReq",
                "/wg/activity/insert",
                HttpCommandType.POST,
                true);

        configuration.addMapper(httpStatement01);
        configuration.addMapper(httpStatement02);

        // 2. 基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        // 3. 创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(configuration,gatewaySessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (null == channel) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            logger.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("netty server gateway start Done! {}", channel.localAddress());

        Thread.sleep(Long.MAX_VALUE);
    }


}
