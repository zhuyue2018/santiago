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
package com.santiago.gateway.netty.netty.ctl;
import com.santiago.commons.dto.req.UnionReq;
import com.santiago.commons.dto.resp.Response;
import com.santiago.commons.util.JsonUtil;
import com.santiago.gateway.netty.domain.GzipAlgorithm;
import com.santiago.gateway.netty.domain.ZipAlgorithm;
import com.santiago.gateway.netty.domain.ZipAlgorithmFactory;
import com.santiago.gateway.netty.netty.annotation.NettyHttpHandler;
import com.santiago.gateway.netty.netty.http.NettyHttpRequest;
import com.santiago.gateway.netty.security.DataSecurity;
import com.santiago.gateway.netty.security.impl.Sm2DataSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


@NettyHttpHandler(path = "/request/body",method = "POST")
public class RequestBodySecurityHandler implements IFunctionHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(RequestBodySecurityHandler.class);
    @Override
    public Response<String> execute(NettyHttpRequest request) {
        /**
         * 可以在此拿到json转成业务需要的对象
         */
        String json = request.contentText();
        UnionReq req = JsonUtil.parseJson(json, UnionReq.class);
        String signature = signature(req.getMsgContent(), "key");
        return Response.ok(json);
    }

    private String signature(String msgContent, String key) {
        Map<String, ZipAlgorithm> map = new HashMap<String, ZipAlgorithm>(16);
        map.put(GzipAlgorithm.ALGORITHM_NAME, new GzipAlgorithm());
        ZipAlgorithmFactory zipAlgorithmFactory = new ZipAlgorithmFactory(map);
        DataSecurity dataSecurity = new Sm2DataSecurity(zipAlgorithmFactory);
        String sign = "";
        try {
            sign = dataSecurity.sign(msgContent, key);
        } catch (Exception e) {
            logger.error("构造签名错误");
        }
        return sign;
    }
}
