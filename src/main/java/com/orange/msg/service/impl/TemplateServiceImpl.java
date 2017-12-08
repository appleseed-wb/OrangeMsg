package com.orange.msg.service.impl;

import com.orange.msg.entity.Template;
import com.orange.msg.repository.TemplateRepository;
import com.orange.msg.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public Template getOne(String uuid) {
        return templateRepository.findOne(uuid);
    }

    @Override
    public void save(Template template) {
        templateRepository.save(template);
    }

    @Override
    public void delete(String uuid) {
        templateRepository.delete(uuid);
    }
}
