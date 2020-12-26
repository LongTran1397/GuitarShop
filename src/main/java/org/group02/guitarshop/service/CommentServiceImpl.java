package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Message;
import org.group02.guitarshop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository repository;

    @Override
    public Integer insertComment(Message message) {
        Message returnedInvoice = repository.save(message);
        return returnedInvoice.getId();
    }

    @Override
    public Message getCommentById(Integer id) {
        Message message = repository.findById(id).get();
        return message;
    }
    
    @Override
    public List<Message> findAllCommentOfProduct(Integer productId) {
    return repository.messageList(productId);
  }
}
