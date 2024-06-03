package com.ltb.gateway.bind;

import com.ltb.gateway.executor.result.SessionResult;

import java.util.Map;

/**
 * 统一泛化调用接口
 *
 * @author leetao
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String,Object> params);
}
