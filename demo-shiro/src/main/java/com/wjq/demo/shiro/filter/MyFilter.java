package com.wjq.demo.shiro.filter;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wjq
 * @since 2021-10-11
 */
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("this is my filter");
        chain.doFilter(request, response);
    }
}
