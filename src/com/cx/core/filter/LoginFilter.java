package com.cx.core.filter;

import com.cx.core.constant.Constant;
import com.cx.core.permission.PermissionCheck;
import com.cx.nsfw.user.entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cx on 2016/10/23.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        //判断当前请求地址是否是登录请求
        if (!uri.contains("/sys/login")){

            if(request.getSession().getAttribute(Constant.USER)!=null){

                //访问纳税服务子系统
                if (uri.contains("/nsfw/")){

                    User user = (User)request.getSession().getAttribute(Constant.USER);

                    //获取Spring容器
                    WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

                    PermissionCheck pc = (PermissionCheck)applicationContext.getBean("permissionCheck");

                    if (pc.isAccessible(user,"nsfw")){

                        //用户有该权限,放行
                        filterChain.doFilter(request,response);

                    }else {
                        response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
                    }

                }else {

                    //非纳税服务子系统,放行
                    filterChain.doFilter(request,response);
                }

            }else {
                response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
            }

        }else{
            //登录请求,放行
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
