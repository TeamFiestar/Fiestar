package com.TeamFiestar.Fiestar.chatting.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	private int messageNo;
	private String messageContent;
	private int senderNo;
	private int targetNo;
	private int chattingNo;
	private String messageSendTime;
	private int chattingRoomNo;
	private int memberNo;
	private int sendAuthority;
	private int artistGroupNo;
	
	private int liveChattingRoomNo;
	private String memberNickname;
	private String memberProfile;

}
