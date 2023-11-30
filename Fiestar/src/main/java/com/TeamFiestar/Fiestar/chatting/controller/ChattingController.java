package com.TeamFiestar.Fiestar.chatting.controller;


import org.apache.ibatis.annotations.Select.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;
import com.TeamFiestar.Fiestar.chatting.service.ChattingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChattingController {
    private final ChattingService service;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return service.createRoom(name);
    }

    @DeleteMapping
    public void deleteRoom(@RequestParam String name) {
    	service.deleteRoom(name);
    }

    @GetMapping
    public String findAllRoom() {
    	List<ChatDto>
    }


}
