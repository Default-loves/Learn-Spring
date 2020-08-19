package com.junyi.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @time: 2020/8/19 14:03
 * @version: 1.0
 * @author: junyi Xu
 * @description: 使用 Cookie
 */
@RestController
public class CookieController {

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response, @RequestParam(name = "username", defaultValue = "123") String username) {
        // 创建一个 cookie
        Cookie cookie = new Cookie("username", username);
        //设置 cookie过期时间
        cookie.setMaxAge(10); // expires in 10 seconds
        //添加到 response 中
        response.addCookie(cookie);

        return "Username is changed!";
    }

    @GetMapping("/")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }

        return "No cookies";
    }



}
