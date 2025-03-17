package com.animalnovels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animalnovels.model.Message;
import com.animalnovels.model.User;
import com.animalnovels.repository.MessageRepository;
import com.animalnovels.repository.UserRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
    
    public List<Message> findAllByOrderBySentAtDesc() {
        return messageRepository.findAllByOrderBySentAtDesc();
    }
    
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }
    
    public Message save(Message message) {
        return messageRepository.save(message);
    }
    
    public Message saveWithUser(Message message, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(message::setUser);
        return messageRepository.save(message);
    }
    
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
