/**
 * Copyright 2013-2033 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.santiago.gateway.netty.netty.controller;
import com.santiago.commons.dto.req.UnionReq;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.util.JsonUtil;
import com.santiago.gateway.netty.domain.SttDetBookDTO;
import com.santiago.gateway.netty.domain.ValidateResult;
import com.santiago.gateway.netty.netty.annotation.NettyHttpHandler;
import com.santiago.gateway.netty.netty.http.NettyHttpRequest;
import com.santiago.gateway.netty.netty.service.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@NettyHttpHandler(path = "/request/body",method = "POST")
public class ClearBookCtrl implements FunctionCtrl {
    private static final Logger logger = LoggerFactory.getLogger(ClearBookCtrl.class);
    @Autowired
    ValidateService validateService;

    @Override
    public UnionResp execute(NettyHttpRequest request) {
        String reqStr = request.contentText();
        UnionReq req = JsonUtil.parseJson(reqStr, UnionReq.class);
        ValidateResult validateResult = validateService.validate(req, SttDetBookDTO.class);
        if (validateResult.getCorrect()) {
            return UnionResp.buildResp("请求成功", "000000", "请求成功");
        } else {
            return UnionResp.buildResp("请求异常", "999999", "请求异常");
        }
    }
}
