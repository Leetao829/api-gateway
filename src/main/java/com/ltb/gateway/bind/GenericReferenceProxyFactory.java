package com.ltb.gateway.bind;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.apache.dubbo.rpc.service.GenericService;
import org.objectweb.asm.Type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 泛化静态代理工厂，并实现缓存
 *
 * @author leetao
 */
public class GenericReferenceProxyFactory {

    private final GenericService genericService;
    private final Map<String,IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public GenericReferenceProxyFactory(GenericService genericService){
        this.genericService = genericService;
    }

    /**
     * 根据方法获取代理对象
     * @param method 方法名称
     * @return 代理对象
     */
    public IGenericReference newInstance(String method){
        return genericReferenceCache.computeIfAbsent(method,k->{
            //泛化调用
            GenericReferenceProxy genericReferenceProxy = new GenericReferenceProxy(genericService,method);
            //创建接口
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(new Signature(method, Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
            Class<?> interfaceClass = interfaceMaker.create();
            //代理对象
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Object.class);
            enhancer.setInterfaces(new Class[]{IGenericReference.class,interfaceClass});
            enhancer.setCallback(genericReferenceProxy);
            return (IGenericReference) enhancer.create();
        });
    }





}
