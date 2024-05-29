package com.ltb.gateway.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * 泛化调用静态代理：发起http请求时，代理到rpc服务
 *
 * @author leetao
 */
public class GenericReferenceProxy implements MethodInterceptor {

    private final GenericService genericService;
    private final String methodName;

    public GenericReferenceProxy(GenericService genericService,String methodName){
        this.genericService = genericService;
        this.methodName = methodName;
    }

    //做一层代理控制
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for(int i = 0;i < parameters.length;i++){
            parameters[i] = parameterTypes[i].getName();
        }
        return genericService.$invoke(methodName,parameters,args);
    }
}
