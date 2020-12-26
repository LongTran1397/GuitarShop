package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Message;
import java.util.List;

public interface CommentService {
    Integer insertComment(Message message);
    Message getCommentById(Integer id);
    public List<Message> findAllCommentOfProduct(Integer productId);
}
