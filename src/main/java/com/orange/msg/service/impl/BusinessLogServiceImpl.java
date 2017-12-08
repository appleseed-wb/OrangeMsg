package com.orange.msg.service.impl;

import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.BusinessLog;
import com.orange.msg.repository.BusinessLogRepository;
import com.orange.msg.service.BusinessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogServiceImpl implements BusinessLogService {

    @Autowired
    private BusinessLogRepository businessLogRepository;

    @Override
    public void saveBusinessLog(List<BusinessLog> list) {
        businessLogRepository.save(list);
    }

    @Override
    public Page<BusinessLog> findByTargetUid(Long id, Pageable pageable) {
        return businessLogRepository.findByTargetUid(id, pageable);
    }

    @Override
    public BusinessLog findByTargetUidAndUuid(Long id, String uuid) {
        return businessLogRepository.findByTargetUidAndUuid(id, uuid);
    }

    @Override
    public void setRead(Long id, String uuid) {
        BusinessLog businessLog = businessLogRepository.findByTargetUidAndUuid(id, uuid);
        businessLog.setStatus(MsgConstant.MSG_STATUS_READ);
        businessLogRepository.save(businessLog);
    }

    @Override
    public List<BusinessLog> findDispatchBusiness(Long id) {
        List<BusinessLog> list = businessLogRepository.findDispatchBusiness(id);
        for (BusinessLog businessLog : list) {
            businessLog.setDispatch(MsgConstant.MSG_DISPATCH_YES);
        }
        businessLogRepository.save(list);
        return list;
    }
}
