package com.orange.msg.repository;

import com.orange.msg.entity.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends MongoRepository<Notice,String> {

    /**
     * 查询大于某个时间点的通知
     * @param operTime
     * @return
     */
    public List<Notice> findByOperTimeGreaterThan(Long operTime);
}
