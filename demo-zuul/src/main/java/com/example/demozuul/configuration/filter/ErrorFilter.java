package com.example.demozuul.configuration.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demobase.core.ResultCode;
import com.example.demobase.utils.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(ResultCode.GATEWAY_ERROR.code);
        Throwable throwable = ctx.getThrowable();
        ctx.remove("throwable");
        log.error("gateway post filter:", throwable);
        return null;
    }
}
