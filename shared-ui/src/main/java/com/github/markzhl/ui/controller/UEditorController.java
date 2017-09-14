package com.github.markzhl.ui.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet(name = "UEditorServlet", urlPatterns = "/UEditor")  
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
        //获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();   
        Resource[] resources = resolver.getResources("/**/config.json");
        String action = request.getParameter("action");    
        String result = new ActionEnter( request, resources[0].getFile().getParent() ).exec();    
        if( action!=null &&     
           (action.equals("listfile") || action.equals("listimage") ) ){    
            rootPath = rootPath.replace("\\", "/");    
            result = result.replaceAll(rootPath, "/");    
        }    
        out.write( result );    
    }  
  
}  
