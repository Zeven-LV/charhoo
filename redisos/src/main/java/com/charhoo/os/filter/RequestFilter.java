package com.charhoo.os.filter;

import com.charhoo.os.session.ThisSession;
import com.charhoo.os.session.ThisSessionUtil;
import com.charhoo.os.session.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter
public class RequestFilter implements Filter {

    private String contextPath;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init;");
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        if(ThisSessionUtil.applicationContext == null){
            //获取Spring容器上下文
            ThisSessionUtil.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
        }

        String requestURI = httpServletRequest.getRequestURI();
        requestURI = requestURI.substring(contextPath.length());
        if(requestURI.contains("html") || requestURI.contains("js") || requestURI.contains("css")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if(requestURI == "/login"){
            String loginName = String.valueOf(httpSession.getAttribute("loginName"));
            String password = String.valueOf(httpSession.getAttribute("password"));
            //登录
        }else{
            ThisSession thisSession = ThreadContext.getSession();
            if(thisSession == null){
                System.out.println("请先登录！");
//                ((HttpServletResponse)servletResponse).setStatus(403);
//                return;
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //选择redis
                filterChain.doFilter(servletRequest,servletResponse);
            }

        }


    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
