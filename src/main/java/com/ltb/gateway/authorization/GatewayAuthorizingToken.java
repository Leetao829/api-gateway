package com.ltb.gateway.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 验证token
 *
 * @author leetao
 */
public class GatewayAuthorizingToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    //通信管道id
    private String uId;

    //JSON WEB TOKEN
    private String jwt;

    public GatewayAuthorizingToken(){}

    public GatewayAuthorizingToken(String uId,String jwt){
        this.uId = uId;
        this.jwt = jwt;
    }


    @Override
    public Object getPrincipal() {
        return uId;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
