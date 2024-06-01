package com.ltb.gateway.datasource;

/**
 * 数据源接口：将Http/Rpc当作数据源使用
 *
 * @author leetao
 */
public interface DataSource {

    /**
     * 获取连接
     * @return 数据源连接
     */
    Connection getConnection();
}
