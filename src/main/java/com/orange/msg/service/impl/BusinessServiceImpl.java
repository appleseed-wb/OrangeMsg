package com.orange.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.Business;
import com.orange.msg.repository.BusinessRepository;
import com.orange.msg.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public void saveBusiness(String uuid,Long time,String msg) {
        Business business = JSON.parseObject(msg,Business.class);
        business.setUuid(uuid);
        business.setStatus(MsgConstant.MSG_STATUS_UNREAD);
        business.setDispatch(MsgConstant.MSG_DISPATCH_NO);
        if (business.getOperTime()==null){
            business.setOperTime(time);
        }
        businessRepository.save(business);
    }

    @Override
    public Page<Business> findAll(Pageable pageable) {
        return businessRepository.findAll(pageable);
    }

    @Override
    public Page<Business> findByTargetUid(Long id, Pageable pageable) {
        return businessRepository.findByTargetUid(id,pageable);
    }

    @Override
    public List<Business> findUnreadMessage(Long id) {
        return businessRepository.findUnreadMessage(id);
    }

    @Override
    public void update(Business business) {
        businessRepository.save(business);
    }

    @Override
    public Business findOne(String uuid) {
        return businessRepository.findOne(uuid);
    }

    @Override
    public void setRead(String uuid) {
        Business business = findOne(uuid);
        business.setStatus(MsgConstant.MSG_STATUS_READ);
        update(business);
    }

    @Override
    public List<Business> findDispatchBusiness(Long id) {
        List<Business> list = businessRepository.findDispatchBusiness(id);
        for (Business business : list) {
            business.setDispatch(MsgConstant.MSG_DISPATCH_YES);
        }
        businessRepository.save(list);
        return list;
    }
}
