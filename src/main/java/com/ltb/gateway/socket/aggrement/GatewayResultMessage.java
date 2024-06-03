package com.ltb.gateway.socket.aggrement;

/**
 * 网关封装结果
 *
 * @author leetao
 */
public class GatewayResultMessage {

    private String code;
    private String info;
    private Object data;

    protected GatewayResultMessage(String code,String info,Object data){
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data){
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(), data);
    }

    public static GatewayResultMessage buildError(String code,String info){
        return new GatewayResultMessage(code,info,null);
    }

    public String getInfo() {
        return info;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
