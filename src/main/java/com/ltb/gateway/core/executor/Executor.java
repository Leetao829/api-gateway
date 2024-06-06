package com.ltb.gateway.core.executor;

import com.ltb.gateway.core.executor.result.SessionResult;
import com.ltb.gateway.core.mapping.HttpStatement;
import java.util.Map;

/**
 * 执行器
 *
 * @author leetao
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String,Object> params) throws Exception;
}
