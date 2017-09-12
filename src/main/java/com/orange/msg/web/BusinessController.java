package com.orange.msg.web;

import com.orange.msg.service.BusinessService;
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

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 业务消息分页查询
     * @param id
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/business/{id}", method = RequestMethod.GET)
    public Object getAll(@PathVariable Long id,@PageableDefault(sort = {"operTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return businessService.findByTargetUid(id,pageable);
    }

    /**
     * 业务消息详情
     * @param id
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/business/{id}/{uuid}", method = RequestMethod.GET)
    public Object getOne(@PathVariable Long id,@PathVariable String uuid){
        return businessService.findOne(uuid);
    }

    /**
     * 业务消息设为已读
     * @param id
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/business/{id}/{uuid}", method = RequestMethod.PUT)
    public Object put(@PathVariable Long id,@PathVariable String uuid){
        businessService.setRead(uuid);
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg","成功");
        return result;
    }
}
