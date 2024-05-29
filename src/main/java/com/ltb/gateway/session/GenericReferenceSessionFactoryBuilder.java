package com.ltb.gateway.session;

import com.ltb.gateway.session.defaults.GenericReferenceSessionFactory;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 会话工厂建造类
 *
 * @author leetao
 */
public class GenericReferenceSessionFactoryBuilder {

    public Future<Channel> build(Configuration configuration){
        IGenericReferenceSessionFactory genericReferenceSessionFactory = new GenericReferenceSessionFactory(configuration);
        try {
            return genericReferenceSessionFactory.openSession();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
