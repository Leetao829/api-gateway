package com.ltb.gateway.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt的签发和解析
 * @author leetao
 */
public class JwtUtil {

    private static final String signingKey = "B*B^5Fe";

    /**
     * 生成jwt格式字符串
     * @param issuer 签发人
     * @param ttlMillis 有效日期
     * @param claims 额外信息
     * @return Token
     */
    public static String encode(String issuer, long ttlMillis, Map<String,Object> claims){
        if(null == claims){
            claims = new HashMap<>();
        }
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                //荷载部分
                .setClaims(claims)
                //签发时间
                .setIssuedAt(now)
                //签发人
                .setSubject(issuer)
                //设置签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, signingKey);
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims decode(String token){
        return Jwts.parser()
                //设置签名的密钥
                .setSigningKey(signingKey)
                //设置需要解析的jwt
                .parseClaimsJws(token)
                .getBody();
    }
}
