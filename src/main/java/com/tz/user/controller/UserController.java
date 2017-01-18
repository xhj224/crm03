package com.tz.user.controller;

import com.tz.entity.User;
import com.tz.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/15 10:22.
 * Project: crm1215.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpServletRequest req) {
        String info = (String) req.getAttribute("info");
        req.setAttribute("info", info);
        return "/WEB-INF/jsp/view/user_login_view.jsp";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGet(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/user/login.do";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest req, HttpServletResponse resp, String username, String password, String token, String noLogin) throws UnsupportedEncodingException {
        String code = (String) req.getServletContext().getAttribute("token");
        if ((username != null && username.length() != 0) && (password != null && password.length() != 0) && (token != null && token.length() != 0)) {
            Cookie cookie;
            if (noLogin != null) {
                //说明要七天免登陆
                cookie = new Cookie("userInfo", URLEncoder.encode(username + ":" + password, "UTF-8"));
                cookie.setMaxAge(7 * 24 * 60 * 60);
                //设置Cookie作用域
                cookie.setPath("/");
                resp.addCookie(cookie);
            }

            //先比对验证码
            if (code.equals(token)) {
                User user = userService.login(username, password);
                if (user != null) {
                    //把用户名放入到session数据范围
                    req.getSession().setAttribute("user", user);
                    req.getSession().setMaxInactiveInterval(60 * 15);
                    //说明登陆成功
                    return "redirect:/emp/1/2/list.do";
                } else {
                    //说明登陆失败
                    return "redirect:/user/login.do?info=1";
                }
            } else {
                return "redirect:/user/login.do?info=2";
            }
        } else {
            return "redirect:/user/login.do";
        }
    }
}
