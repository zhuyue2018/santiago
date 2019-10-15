package com.santiago.commons.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.santiago.commons.dto.exception.JsonUtilException;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonUtil {

    private ObjectMapper mapper = new ObjectMapper();

    JsonUtil() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
    }

    public static JsonUtil create() {
        return new JsonUtil();
    }


    public JsonUtil setDateFormat(String pattern) {
        mapper.setDateFormat(new SimpleDateFormat(pattern));
        return this;
    }

    public JsonUtil setNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        mapper.setPropertyNamingStrategy(propertyNamingStrategy);
        return this;
    }

    public JsonUtil unwrapRootValue() {
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        return this;
    }

    public JsonUtil wrapRootValue() {
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        return this;
    }

    public JsonUtil setFilterProvider(FilterProvider filterProvider) {
        mapper.setFilterProvider(filterProvider);
        return this;
    }

    public String objectToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonUtilException("Failed to convert object to json", e);
        }
    }

    public <T> T jsonToObject(String responseJson, Class<T> clazz) {
        try {
            return mapper.readValue(responseJson, clazz);
        } catch (IOException e) {
            throw new JsonUtilException("Failed to convert json to object", e);
        }
    }

    public JsonNode getJsonNode(String jsonStr) {
        try {
            return mapper.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new JsonUtilException("Failed to convert request to JsonNode",
                                        e);
        } catch (IOException e) {
            throw new JsonUtilException("IO exception", e);
        }
    }

}