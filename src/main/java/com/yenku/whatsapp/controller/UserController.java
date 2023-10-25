package com.yenku.whatsapp.controller;

import com.yenku.whatsapp.exception.UserException;
import com.yenku.whatsapp.model.User;
import com.yenku.whatsapp.request.UpdateUserRequest;
import com.yenku.whatsapp.response.ApiResponse;
import com.yenku.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserprofileHandler(@RequestHeader("Authorization") String token) throws UserException {

        User user=userService.findUserProfile(token);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable ("query") String q) {
        List<User> users = userService.searchUser(q);

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse>updateDeserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);

        userService.updateUser(user.getId(), req);

        ApiResponse res = new ApiResponse("user updated successfully", true);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }
}
