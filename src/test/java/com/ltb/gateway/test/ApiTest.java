package com.ltb.gateway.test;

import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.session.GenericReferenceSessionFactoryBuilder;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_GenericReference() throws InterruptedException, ExecutionException {
        Configuration configuration = new Configuration();
        configuration.addGenericReference("api-gateway-test","com.ltb.gateway.rpc.IActivityBooth","sayHi");
        GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = builder.build(configuration);
        logger.info("服务启动完成{}",future.get().id());
        Thread.sleep(Long.MAX_VALUE);
    }
}
