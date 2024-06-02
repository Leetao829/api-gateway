package com.ltb.gateway.executor;

import com.ltb.gateway.executor.result.GatewayResult;
import com.ltb.gateway.mapping.HttpStatement;
import java.util.Map;

/**
 * 执行器
 *
 * @author leetao
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String,Object> params) throws Exception;
}
