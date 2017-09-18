package com.github.markzhl.admin.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.GroupType;
import com.github.markzhl.admin.service.GroupTypeService;
import com.github.markzhl.common.msg.TableResultResponse;
import com.github.markzhl.common.rest.BaseController;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("groupType")
public class GroupTypeController extends BaseController<GroupTypeService,GroupType> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<GroupType> page(int limit, int offset, String name){
        Example example = new Example(GroupType.class);
        if(StringUtils.isNotBlank(name))
            example.createCriteria().andLike("name", "%" + name + "%");
        int count = baseService.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<GroupType>(count,baseService.selectByExample(example));
    }

}
