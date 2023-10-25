package com.yenku.whatsapp.service;

import com.yenku.whatsapp.exception.ChatException;
import com.yenku.whatsapp.exception.MessageException;
import com.yenku.whatsapp.exception.UserException;
import com.yenku.whatsapp.model.Message;
import com.yenku.whatsapp.model.User;
import com.yenku.whatsapp.request.SendMessageRequest;

import java.util.List;

public interface MessageService {

    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;

    public List<Message> getChatsMessages(Integer chatId, User reqUser) throws ChatException, UserException;

    public Message findMessageById(Integer messageId) throws MessageException;

    public void deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException;
}
