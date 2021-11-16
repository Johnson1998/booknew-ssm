package com.john.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author John
 * @create 2021-11-0621:48
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Object user =  httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(servletRequest,
                    servletResponse);

        }else {
            filterChain.doFilter(servletRequest, servletResponse);

        }
        System.out.println("filter-do");
    }

    @Override
    public void destroy() {

    }
}
