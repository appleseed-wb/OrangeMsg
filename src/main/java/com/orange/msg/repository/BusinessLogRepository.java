package com.orange.msg.repository;

import com.orange.msg.entity.BusinessLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessLogRepository extends MongoRepository<BusinessLog,String> {

    /**
     * 根据用户id分页查询
     * @param id
     * @param pageable
     * @return
     */
    public Page<BusinessLog> findByTargetUid(Long id, Pageable pageable);

    /**
     * 根据用户id和uuid获取通知
     * @param id
     * @param uuid
     * @return
     */
    public BusinessLog findByTargetUidAndUuid(Long id, String uuid);

    @Query("{dispatch:1,targetUid:?0}")
    public List<BusinessLog> findDispatchBusiness(Long id);
}
