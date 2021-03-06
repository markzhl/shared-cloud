package com.github.markzhl.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author mark
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;
    public String getCurrentUserName(){
        String authorization = request.getHeader("Authorization");
        return new String(Base64Utils.decodeFromString(authorization));
    }
}
