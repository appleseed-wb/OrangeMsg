package com.orange.msg.repository;

import com.orange.msg.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends MongoRepository<Business,String> {

    @Query("{status:1,targetUid:?0}")
    public List<Business> findUnreadMessage(Long id);

    public Page<Business> findByTargetUid(Long id, Pageable pageable);

    @Query("{dispatch:1,targetUid:?0}")
    public List<Business> findDispatchBusiness(Long id);
}
