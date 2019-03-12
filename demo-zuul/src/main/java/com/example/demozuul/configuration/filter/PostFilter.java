package com.example.demozuul.configuration.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demobase.core.ResultCode;
import com.example.demobase.utils.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return -2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return HttpStatus.OK.value() != ctx.getResponseStatusCode()
                && HttpStatus.CREATED.value() != ctx.getResponseStatusCode();
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        if (throwable != null) {
            log.error("gateway post filter:", throwable);
        }
        ctx.setResponseStatusCode(HttpStatus.OK.value());
        try {
            JSONObject data = JsonUtils.initJSONObject(ResultCode.GATEWAY_ERROR);
            HttpServletResponse response = ctx.getResponse();response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setStatus(200);
            ServletOutputStream output = response.getOutputStream();
            output.write(data.toJSONString().getBytes());
            output.flush();
            output.close();
        }catch (Exception e){
            log.error("error:{}", e);
        }
        return null;
    }
}
