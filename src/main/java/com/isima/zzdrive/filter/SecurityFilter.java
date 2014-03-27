/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.filter;

import com.isima.zzdrive.bean.UserBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {
    
    /**
     * Checks if user is logged in. If not it redirects to the login page.
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserBean userBean = (UserBean)((HttpServletRequest)request).getSession().getAttribute("userBean");
         
        if (userBean == null || !userBean.isLogged()) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            System.out.println(contextPath + "/login");
            ((HttpServletResponse)response).sendRedirect(contextPath + "/login");
        }
        else {
            chain.doFilter(request, response);   
        }
    }
 
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
 
    @Override
    public void destroy() {
    }
}
