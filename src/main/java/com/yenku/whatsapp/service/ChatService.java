package com.yenku.whatsapp.service;

import com.yenku.whatsapp.exception.ChatException;
import com.yenku.whatsapp.exception.UserException;
import com.yenku.whatsapp.model.Chat;
import com.yenku.whatsapp.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, Integer userid2) throws UserException;

    public Chat findChatById(Integer chatId) throws ChatException;

    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;

    public Chat createGroup(GroupChatRequest req, User reqUer) throws UserException;

    public Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException, ChatException;

    public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException;

    public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException;

    public void deleteChat(Integer chatId, Integer userId) throws ChatException, UserException;

}
