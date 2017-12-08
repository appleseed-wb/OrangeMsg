package com.orange.msg.repository;

import com.orange.msg.entity.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends MongoRepository<Template,String> {
}
