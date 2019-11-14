package com.santiago.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiago.commons.util.MyMapper;
import com.santiago.core.mapper.MerchantInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Controller
public class UnionQueryCtrl {
    @Autowired
    MerchantInfoMapper merchantInfoMapper;

    @RequestMapping(value = "/union/query")
    public Object unionQuery(UnionQueryDTO unionQueryDTO) {
        String queryCode = unionQueryDTO.getQueryCode();
        QueryEntity queryEntity = getQueryEntity(queryCode);
        QueryDTO queryDTO = buildQueryDTO(queryEntity, unionQueryDTO.getContent());
        Object o = doQuery(queryEntity, queryDTO);


    }

    private Object doQuery(QueryEntity queryEntity, QueryDTO queryDTO) throws IllegalAccessException {
        Example example = new Example(queryDTO.getClass());
        Example.Criteria criteria = example.createCriteria();
        Field[] declaredFields = queryDTO.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            String fieldValue = f.get(queryDTO).toString();
            if (null != fieldValue) {
                if (fieldValue.contains("-")) {
                    String[] strings = fieldValue.split("-");
                    if (!"infinity".equals(strings[0])) {
                        criteria.andGreaterThanOrEqualTo(f.getName(), strings[0]);
                    }
                    if (!"infinity".equals(strings[1])) {
                        criteria.andLessThanOrEqualTo(f.getName(), strings[1]);
                    }
                } else {
                    criteria.andEqualTo(f.getName(), fieldValue);
                }
            }
        }
        MyMapper mapper = getMapper(queryEntity);
        List list = mapper.selectByExample(example);
        return list;
    }

    private QueryDTO buildQueryDTO(QueryEntity queryEntity, String content) throws ClassNotFoundException, IOException {
        String classRef = queryEntity.getClassRef();
        ObjectMapper objectMapper = new ObjectMapper();
        if (queryEntity.getSimple()) {
            return (QueryDTO) objectMapper.readValue(content, Class.forName(classRef));
        } else {
            return null;
        }
    }

    private QueryEntity getQueryEntity(String queryCode) {
        return null;
    }
}
