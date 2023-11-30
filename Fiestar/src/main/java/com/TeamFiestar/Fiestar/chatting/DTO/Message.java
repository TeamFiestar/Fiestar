package com.TeamFiestar.Fiestar.chatting.DTO;

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
	private String sendTime;
}
