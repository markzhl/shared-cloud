package com.github.markzhl.admin.controller.sys;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.entity.sys.GateClient;
import com.github.markzhl.admin.service.sys.GateClientService;
import com.github.markzhl.common.base.BaseController;
import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.common.msg.TableResultResponse;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Controller
@RequestMapping("/sys/gateClient")
public class GateClientController extends BaseController<GateClientService,GateClient> {
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<GateClient> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
    	Page<GateClient> page = new Page<GateClient>();
    	page.setSize(limit);
    	page.setCurrent(offset);
    	Wrapper<GateClient> wrapper = new EntityWrapper<GateClient>(new GateClient());
    	if(StringUtils.isNotBlank(name)){
    		wrapper.like("code", "%" + name + "%").or().
    		like("name", "%" + name + "%");
    	}
    	page = baseService.selectPage(page, wrapper);
        return new TableResultResponse<GateClient>(page.getRecords().size(),page.getRecords());
    }

    @RequestMapping(value = "/{id}/lock",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<GateClient> updateLock(GateClient entity){
        baseService.updateById(entity);
        return new ObjectRestResponse<GateClient>().rel(true);
    }

    @RequestMapping(value = "/{id}/service", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifiyServices(@PathVariable Long id,String services){
        baseService.modifyClientServices(id, services);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/service", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<BaseResource>> getServices(@PathVariable Long id){
        return new ObjectRestResponse<List<BaseResource>>().rel(true).result(baseService.getClientServices(id));
    }
}
