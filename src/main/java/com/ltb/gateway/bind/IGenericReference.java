package com.ltb.gateway.bind;

import java.util.Map;

/**
 * 统一泛化调用接口
 *
 * @author leetao
 */
public interface IGenericReference {

    Object $invoke(Map<String,Object> params);
}
