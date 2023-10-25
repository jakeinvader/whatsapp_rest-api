package com.yenku.whatsapp.service;

import com.yenku.whatsapp.exception.UserException;
import com.yenku.whatsapp.model.User;
import com.yenku.whatsapp.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    public User findUserById(Integer id) throws UserException;
    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);
}
