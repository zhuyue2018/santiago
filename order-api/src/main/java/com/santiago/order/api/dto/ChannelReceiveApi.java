package com.santiago.order.api.dto;

import com.santiago.commons.dto.resp.UnionResult;

public interface ChannelReceiveApi {
    UnionResult<Object> receive(WeixinNotifyRequest request);
}
