package com.service.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestFilter extends ZuulFilter {
    /**
     * @description 设置过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        // 过滤器类型为前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    /**
     * @description 设置过滤器执行优先级
     * @return
     */
    @Override
    public int filterOrder() {
        // 过滤器执行优先级
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    /**
     * @description 是否启用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 使用boolean值控制是否启用
        return true;
    }

    /**
     * @description 过滤器的实现逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取Request对象
        HttpServletRequest request = ctx.getRequest();
        // 从请求当中拿到access-token
        String token = request.getParameter("access-token");

        if (StringUtils.isBlank(token)) {
            // 如果请求当中的access-token不存在,则拦截(false)
            ctx.setSendZuulResponse(false);
            // 设置返回的HttpStatusCode为403
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
