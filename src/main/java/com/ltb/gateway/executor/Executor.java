package com.ltb.gateway.executor;

import com.ltb.gateway.executor.result.SessionResult;
import com.ltb.gateway.mapping.HttpStatement;
import java.util.Map;

/**
 * 执行器
 *
 * @author leetao
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String,Object> params) throws Exception;
}
