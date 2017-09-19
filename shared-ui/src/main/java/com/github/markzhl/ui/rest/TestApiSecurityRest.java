package com.github.markzhl.ui.rest;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.ui.rpc.TestApiSecurityClient;
@Controller
@RequestMapping("apisec")
public class TestApiSecurityRest {
	@Autowired
	TestApiSecurityClient testApiSecurityClient;
	
	@RequestMapping("test/{username}")
    public @ResponseBody String test(@PathVariable("username") String username) throws ExecutionException, InterruptedException {
        return testApiSecurityClient.testApiGateSecurity(username).getName();
    }
}
