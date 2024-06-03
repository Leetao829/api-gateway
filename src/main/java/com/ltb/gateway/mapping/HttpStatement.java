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
    //限定参数类型，只能是一种类型：new String[]{"java.lang.String"} 、new String[]{"com.ltb.gateway.rpc.dto.XReq"}
    private String parameterType;
    //uri
    private String uri;
    //请求类型
    private HttpCommandType httpCommandType;
    //是否需要鉴权
    private boolean auth;

    public HttpStatement(String application,String interfaceName,String methodName,String parameterType,String uri,HttpCommandType httpCommandType,boolean auth){
        this.application = application;
        this.methodName = methodName;
        this.interfaceName = interfaceName;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.parameterType = parameterType;
        this.auth = auth;
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

    public String getParameterType() {
        return parameterType;
    }

    public boolean isAuth() {
        return auth;
    }
}
