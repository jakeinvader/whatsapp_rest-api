package com.yenku.whatsapp.controller;

import com.yenku.whatsapp.exception.ChatException;
import com.yenku.whatsapp.exception.MessageException;
import com.yenku.whatsapp.exception.UserException;
import com.yenku.whatsapp.model.Message;
import com.yenku.whatsapp.model.User;
import com.yenku.whatsapp.request.SendMessageRequest;
import com.yenku.whatsapp.response.ApiResponse;
import com.yenku.whatsapp.service.MessageService;
import com.yenku.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {

        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {

        User user = userService.findUserProfile(jwt);

        req.setUserId(user.getId());
        Message message = messageService.sendMessage(req);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatMessagesHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {

        User user = userService.findUserProfile(jwt);

        List<Message> messages = messageService.getChatsMessages(chatId, user);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) throws UserException, MessageException {

        User user = userService.findUserProfile(jwt);

        messageService.deleteMessage(messageId, user);

        ApiResponse res = new ApiResponse("message deleted successfully", false);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
