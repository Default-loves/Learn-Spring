package com.junyi.bean.scope.web.controller;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description:
 */
@Controller
public class IndexController {
    @Autowired
    private User user;  //CGLib代理后的对象，其是一直不变的

    @GetMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("user", user);
        return "index";
    }
}
