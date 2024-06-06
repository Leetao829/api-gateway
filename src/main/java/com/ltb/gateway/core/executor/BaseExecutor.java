package com.ltb.gateway.core.executor;

import com.alibaba.fastjson.JSON;
import com.ltb.gateway.core.executor.result.SessionResult;
import com.ltb.gateway.core.type.SimpleTypeRegistry;
import com.ltb.gateway.core.datasource.Connection;
import com.ltb.gateway.core.mapping.HttpStatement;
import com.ltb.gateway.core.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public abstract class BaseExecutor implements Executor{

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Connection connection;

    public BaseExecutor(Configuration configuration,Connection connection){
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) {
        //参数处理，后续的一些参数校验也可以在这里进行封装
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};
        logger.info("执行调用 method：{}#{}.{}({}) args：{}", httpStatement.getApplication(), httpStatement.getInterfaceName(), httpStatement.getMethodName(), JSON.toJSONString(parameterTypes), JSON.toJSONString(args));
        Object data = doExec(methodName,parameterTypes,args);
        return SessionResult.buildSuccess(data);
    }


    protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);
}
