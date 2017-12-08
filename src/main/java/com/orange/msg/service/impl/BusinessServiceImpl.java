package com.orange.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.orange.msg.entity.Business;
import com.orange.msg.repository.BusinessRepository;
import com.orange.msg.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Business save(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Business saveBusiness(String uuid,Long time,String msg) {
        Business business = JSON.parseObject(msg,Business.class);
        business.setUuid(uuid);
        if (business.getOperTime()==null){
            business.setOperTime(time);
        }
        return businessRepository.save(business);
    }

    @Override
    public Page<Business> findAll(Pageable pageable) {
        return businessRepository.findAll(pageable);
    }

    @Override
    public void update(Business business) {
        businessRepository.save(business);
    }

    @Override
    public Business findOne(String uuid) {
        return businessRepository.findOne(uuid);
    }

}
