package com.orange.msg.repository;

import com.orange.msg.entity.NoticeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeLogRepository extends MongoRepository<NoticeLog,String> {

    public Page<NoticeLog> findByTargetUidAndActionAndStatus(Long id, String action, Integer status, Pageable pageable);

    /**
     * 获取用户最后一条通知
     * @param id
     * @return
     */
    public NoticeLog findByTargetUidOrderByOperTimeDesc(Long id);

    /**
     * 查询用户未读通知
     * @param id
     * @return
     */
    @Query("{dispatch:1,targetUid:?0}")
    public List<NoticeLog> findDispatchNotice(Long id);

    /**
     * 根据用户id和uuid获取通知
     * @param id
     * @param uuid
     * @return
     */
    public NoticeLog findByTargetUidAndUuid(Long id,String uuid);
}
