package com.TeamFiestar.Fiestar.chatting.DTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.service.ChattingService;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, Message chatMessage, ChattingService chatService) {
        // message 에 담긴 타입을 확인한다.
        // 이때 message 에서 getType 으로 가져온 내용이
        // ChatDTO 의 열거형인 MessageType 안에 있는 ENTER 과 동일한 값이라면
        if (chatMessage.getType().equals(Message.MessageType.ENTER)) {
            // sessions 에 넘어온 session 을 담고,
            sessions.add(session);
            // message 에는 입장하였다는 메시지를 띄운다
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
            sendMessage(null, null);
        } else if (chatMessage.getType().equals(Message.MessageType.TALK)){
            //메세지 보내기
            chatMessage.setMessage(chatMessage.getMessage());
            sendMessage(null, null);
        }
    }


    private <T> void sendMessage(T message, ChattingService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}
