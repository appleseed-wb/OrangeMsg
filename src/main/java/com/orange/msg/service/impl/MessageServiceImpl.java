package com.orange.msg.service.impl;

import com.orange.msg.entity.Message;
import com.orange.msg.repository.MessageRepository;
import com.orange.msg.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message findOne(String uuid) {
        return messageRepository.findOne(uuid);
    }
}
