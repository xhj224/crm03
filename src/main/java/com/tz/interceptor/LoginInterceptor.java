package com.tz.interceptor;

import com.tz.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 22:06
 */
public class LoginInterceptor implements HandlerInterceptor {
    private List<String> allowedPath;

    public void setAllowedPath(List<String> allowedPath) {
        this.allowedPath = allowedPath;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURL().toString();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        for (String path : allowedPath) {
            if (url.endsWith(path)) {
                return true;
            }
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/login.do");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
