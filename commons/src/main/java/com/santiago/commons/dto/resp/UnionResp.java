package com.santiago.commons.dto.resp;

import com.santiago.commons.util.JsonUtil;
import com.santiago.commons.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: sac-fa
 * @description:
 * @author: zhuyue
 * @create: 2019-11-21 10:16
 **/
public class UnionResp {

    private static final Logger logger = LoggerFactory.getLogger(UnionResp.class);
    private String code;
    private String msg;
    private String serialNo;
    private String sign;
    private String body;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public void setBodyWithBase64(Object resp) throws IOException {
/*        if (null == resp) {
            return;
        }
        String str = JsonUtil.obj2JsonStr(resp);
        this.body = new Base64().encodeToString(str.getBytes("UTF-8"));*/
        if (null == resp) {
            return;
        }
        try {
            String respStr = ZipUtil.zipBytesToString(JsonUtil.obj2JsonStrExcludeNull(resp).getBytes("UTF-8"), true);
            logger.info("返回应答报文:{}", respStr);
            this.body= respStr;
        }
        catch(Exception e) {
            logger.info("压缩异常============="+e);
        }


    }

    public static UnionResp buildResp(Object resp, String code, String msg) throws IOException {
        UnionResp unionResp = new UnionResp();
        unionResp.setCode(code);
        unionResp.setMsg(msg);
        unionResp.setBodyWithBase64(resp);
//        unionResp.setBody(JsonUtil.obj2JsonStr(resp));
        /*logger.info("返回应答报文:{}", JsonUtil.obj2JsonStr(unionResp));*/
        return unionResp;
    }

}