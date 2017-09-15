package com.github.markzhl.ui.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.baidu.ueditor.ActionEnter;
import com.github.markzhl.common.util.JacksonConverter;

@WebServlet(name = "UEditorServlet", urlPatterns = "/plugins/ueditor/UEditor")  
public class UEditorController extends HttpServlet {  
  
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{  
        doPost(request, response);  
    }  
      
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        request.setCharacterEncoding( "utf-8" );    
        response.setHeader("Content-Type" , "text/html");    
        PrintWriter out = response.getWriter();  
        ServletContext application=this.getServletContext();  
        String rootPath = application.getRealPath( "/" );    
        String action = request.getParameter("action"); 
        //获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();   
        Resource[] resources = resolver.getResources("/**/config.json");
        rootPath = resources[0].getFile().getParent();
        rootPath = rootPath.substring(0, rootPath.indexOf(File.separator+"plugins"+File.separator+"ueditor"));
        String result = new ActionEnter( request, rootPath).exec();
        System.out.println(action);
        System.out.println(result);
        if(action != null && action.equals("uploadimage")){
        	Map resultMap = JacksonConverter.getInstance().json2Map(result);
        	if(resultMap != null && resultMap.containsKey("state") && resultMap.get("state").toString().equals("SUCCESS")) {
        		if(resultMap.containsKey("url")){
        			resultMap.put("url", "/admin"+resultMap.get("url"));
        		}
        	}
        	result = JacksonConverter.getInstance().toJson(resultMap);
        }
        if( action!=null &&     
           (action.equals("listfile") || action.equals("listimage") ) ){    
            rootPath = rootPath.replace("\\", "/");    
            result = result.replaceAll(rootPath, "/");    
        }    
        out.write( result );    
    }  
  
}  
