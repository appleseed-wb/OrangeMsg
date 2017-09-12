package com.orange.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.orange.msg.entity.Notice;
import com.orange.msg.repository.NoticeRepository;
import com.orange.msg.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public void saveNotice(String uuid,Long time,String msg) {
        Notice notice = JSON.parseObject(msg,Notice.class);
        notice.setUuid(uuid);
        if (notice.getOperTime()==null){
            notice.setOperTime(time);
        }
        noticeRepository.save(notice);
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    @Override
    public void update(Notice business) {
        noticeRepository.save(business);
    }

    @Override
    public Notice findOne(String uuid) {
        return noticeRepository.findOne(uuid);
    }

    @Override
    public List<Notice> findDispatchNotice(Long operTime) {
        return noticeRepository.findByOperTimeGreaterThan(operTime);
    }
}
