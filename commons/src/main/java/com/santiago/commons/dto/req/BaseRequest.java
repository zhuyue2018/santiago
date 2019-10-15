package com.santiago.commons.dto.req;

/**
 * @author sean
 * @Description: ${TODO}
 * @date 18-12-3 上午9:50
 */
public interface BaseRequest<T> {
    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    public Class<T> getResponseClass();
}
