package com.santiago.account.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
@Component
public class JSONArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JSONRequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //得到 JSONRequestParam 注解信息并将其转换成用来记录注解信息的 JSONRequestParamNamedValueInfo 对象
        JSONRequestParam jsonRequestParam = parameter.getParameterAnnotation(JSONRequestParam.class);
        JSONRequestParamNamedValueInfo namedValueInfo = new JSONRequestParamNamedValueInfo(jsonRequestParam.name(), jsonRequestParam.required());
        if (namedValueInfo.name.isEmpty()) {
            namedValueInfo.name = parameter.getParameterName();
            if (namedValueInfo.name == null) {
                throw new IllegalArgumentException(
                        "Name for argument type [" + parameter.getNestedParameterType().getName() +
                                "] not available, and parameter name information not found in class file either.");
            }
        }
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        //获得对应的 value 的 JSON 字符串
        String jsonText = servletRequest.getParameter(namedValueInfo.name);
        //得到参数的 Class
        Class clazz = parameter.getParameterType();
        //使用 Jackson 将 JSON 字符串转换成我们想要的对象类
        ObjectMapper mapper = new ObjectMapper();
        Object value = mapper.readValue(jsonText, clazz);
        return value;
    }

    private class JSONRequestParamNamedValueInfo {
        private String name;
        private boolean required;
        public JSONRequestParamNamedValueInfo(String name, boolean required) {
            this.name = name;
            this.required = required;
        }
    }
}