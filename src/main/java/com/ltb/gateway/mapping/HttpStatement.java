package com.ltb.gateway.mapping;

/**
 * 网关接口映射信息
 *
 * @author leetao
 */
public class HttpStatement {

    //应用名称
    private String application;
    //接口名称
    private String interfaceName;
    //方法名称
    private String methodName;
    //uri
    private String uri;
    //请求类型
    private HttpCommandType httpCommandType;

    public HttpStatement(String application,String interfaceName,String methodName,String uri,HttpCommandType httpCommandType){
        this.application = application;
        this.methodName = methodName;
        this.interfaceName = interfaceName;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }

    public String getApplication() {
        return application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUri() {
        return uri;
    }
}
