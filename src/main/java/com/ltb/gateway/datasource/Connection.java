package com.ltb.gateway.datasource;

/**
 * 连接接口
 *
 * @author leetao
 */
public interface Connection {

    /**
     * 执行操作
     * @param method 方法名称
     * @param parameterTypes 参数类型
     * @param parameterNames 参数名称
     * @param args 参数值
     * @return 调用返回结果
     */
    Object execute(String method,String[] parameterTypes,String[] parameterNames,Object[] args);
}
