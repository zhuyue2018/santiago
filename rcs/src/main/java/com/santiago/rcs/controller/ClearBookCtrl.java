package com.santiago.rcs.controller;

import com.santiago.commons.annotation.LogParams;
import com.santiago.commons.dto.req.UnionInnerReq;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.enums.HttpStatusEnum;
import com.santiago.commons.util.ValidateUtil;
import com.santiago.rcs.entity.domain.ClearBookDTO;
import com.santiago.rcs.service.ClearBookService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/clearBooks")
public class ClearBookCtrl {
    private static final Logger logger = LoggerFactory.getLogger(ClearBookCtrl.class);
    private final ClearBookService clearBookService;

    public ClearBookCtrl(@Autowired ClearBookService clearBookService) {
        this.clearBookService = clearBookService;
    }

    @PostMapping(value = "")
    @LogParams
    public UnionResp receive(@RequestBody @Valid UnionInnerReq req, BindingResult result) {
        logger.info("接收分账信息开始，报文为:{}", req.getMsgContent());
        Map errorMap = ValidateUtil.validateReq(req, result, ClearBookDTO.class);
        if (errorMap.size() > 0) {
            return new UnionResp("400", "请求参数异常，请检查!", errorMap);
        }
        clearBookService.receiveClearBook(req.getContent(ClearBookDTO.class));
        return new UnionResp("200", "请求成功!");
    }

    @PatchMapping(value = "")
    public UnionResp update() {
        return UnionResp.success();
    }


}
