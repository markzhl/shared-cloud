package com.github.markzhl.common.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.service.BaseService;

/**
 * 
 *
 * @author mark
 * @create 2017-06-15 8:48
 */
public class BaseController<Servcie extends BaseService,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Servcie baseService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Entity> add(Entity entity){
        baseService.insertSelective(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable int id){
        return new ObjectRestResponse<Entity>().rel(true).result(baseService.selectById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(Entity entity){
        baseService.updateSelectiveById(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable int id){
        baseService.deleteById(id);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> list(){
        return baseService.selectListAll();
    }

    public String getCurrentUserName(){
        String authorization = request.getHeader("Authorization");
        return new String(Base64Utils.decodeFromString(authorization));
    }
}
