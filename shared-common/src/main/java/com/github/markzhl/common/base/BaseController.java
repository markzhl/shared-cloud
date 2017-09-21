package com.github.markzhl.common.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.service.IService;
import com.github.markzhl.common.msg.ObjectRestResponse;

/**
 * 
 *
 * @author mark
 */
public class BaseController<Servcie extends IService,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Servcie baseService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Entity> add(Entity entity){
        baseService.insert(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable Long id){
        return new ObjectRestResponse<Entity>().rel(true).result(baseService.selectById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(Entity entity){
        baseService.updateById(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable Long id){
        baseService.deleteById(id);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> list(){
        return baseService.selectList(null);
    }

    public String getCurrentUserName(){
        String authorization = request.getHeader("Authorization");
        return new String(Base64Utils.decodeFromString(authorization));
    }
}
