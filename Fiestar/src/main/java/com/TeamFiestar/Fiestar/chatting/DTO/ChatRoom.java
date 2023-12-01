package com.TeamFiestar.Fiestar.chatting.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoom {
    private int chattingNo;
    private String lastMessage;
    private String sendTime;
    private int targetNo;
    private String targetNickname;
    private String targetProfile;
    private int notReadCount;
    private int maxMessageNo;
    
}

