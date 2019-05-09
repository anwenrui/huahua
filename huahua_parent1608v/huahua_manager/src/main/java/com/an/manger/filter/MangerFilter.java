package com.an.manger.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import huahua.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class MangerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre"; //前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0; //优先级，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; //过滤器开关，true表示开启
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Zuul过滤器");
        //向header中添加鉴权令牌
        RequestContext requestContext=RequestContext.getCurrentContext();
        //获取header
        HttpServletRequest request=requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")){
            return null;
        }
        String url = request.getRequestURI().toString();
        if (url.indexOf("/admin/login")>0){
            System.out.println("登录页面"+url);
            return null;
        }

        String header = (String) request.getHeader("Authorization"); //获取头信息
        if (null!=header && header.startsWith("Bearer ")){
            String token = header.substring(7);
            Claims claims= jwtUtil.parseJWT(token);
            if (null!=claims){
                if ("admin".equals(claims.get("roles"))){
                    requestContext.addZuulRequestHeader("Authorization",header);
                    System.out.println("token 验证通过 添加了头信息"+header);
                    return null;
                }
            }
        }

        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(401);//http状态码
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");

        return null;
    }
}