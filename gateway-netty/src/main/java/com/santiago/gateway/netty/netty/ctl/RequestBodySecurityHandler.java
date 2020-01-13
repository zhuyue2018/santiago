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
import com.santiago.commons.security.DataSecurity;
import com.santiago.commons.util.JsonUtil;
import com.santiago.commons.domain.GZipAlgorithm;
import com.santiago.commons.domain.ZipAlgorithm;
import com.santiago.commons.domain.ZipAlgorithmFactory;
import com.santiago.gateway.netty.netty.annotation.NettyHttpHandler;
import com.santiago.gateway.netty.netty.http.NettyHttpRequest;
import com.santiago.commons.security.impl.Sm2DataSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;



