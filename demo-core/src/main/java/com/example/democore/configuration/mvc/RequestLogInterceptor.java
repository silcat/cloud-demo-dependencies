package com.example.democore.configuration.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Optional;

/**
 * 打印请求参数
 */
@Slf4j
public class RequestLogInterceptor implements HandlerInterceptor {

    private ManagementServerProperties managementServerProperties;

    public RequestLogInterceptor(ManagementServerProperties managementServerProperties) {
        this.managementServerProperties = managementServerProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        //actuator请求不打印日志
        if (requestURI.startsWith(managementServerProperties.getContextPath())){
            return true;
        }
        StringBuffer requestLog = new StringBuffer();
        requestLog.append("request:")
                .append(request.getRequestURI())
                .append(", params:{");

        Enumeration<String> params = request.getParameterNames();
        int index = 0;
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String val = Optional.ofNullable(request.getParameter(name)).isPresent() ? request.getParameter(name):"null";
            if (index > 0){
                requestLog.append(",");
            }
            requestLog.append(name).append(": ").append(val);
            index++;
        }
        requestLog.append("}");

        log.info(requestLog.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
