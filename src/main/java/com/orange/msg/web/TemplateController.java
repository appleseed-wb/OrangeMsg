package com.orange.msg.web;

import com.orange.msg.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息模版
 */
@RestController
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 消息模版分页查询
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public Object getAll(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return null;
    }

    /**
     * 创建消息模版
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public Object create(@PathVariable String uuid){
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg","成功");
        return result;
    }

    /**
     * 消息模版详情
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/template/{uuid}", method = RequestMethod.GET)
    public Object getOne(@PathVariable String uuid){
        return templateService.getOne(uuid);
    }

    /**
     * 更新消息模版
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/template/{uuid}", method = RequestMethod.PUT)
    public Object update(@PathVariable String uuid){
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg","成功");
        return result;
    }

    /**
     * 删除消息模版
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/template/{uuid}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String uuid){
        templateService.delete(uuid);
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg", "成功");
        return result;
    }

}
