package com.TeamFiestar.Fiestar.board.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardImg {

	private int imgNo;
	private int boardNo;
	private String boardImagePath;
	private int boardImageOrder;
	private String boardImageRename;
	private String boardImageOriginalName;
	
	
	private MultipartFile uploadFile;

	
	
	
}
