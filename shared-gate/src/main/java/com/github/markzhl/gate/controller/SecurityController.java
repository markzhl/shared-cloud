package com.github.markzhl.gate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 *
 * @author mark
 */
@Controller
public class SecurityController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
