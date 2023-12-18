package com.TeamFiestar.Fiestar.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.BoardImg;
import com.TeamFiestar.Fiestar.board.model.exception.BoardWriteException;
import com.TeamFiestar.Fiestar.board.model.mapper.EditBoardMapper;
import com.TeamFiestar.Fiestar.common.utility.Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;
	
	@Value("${my.board.location}")
	private String folderPath; // 서버 저장 폴더 경로
	
	@Value("${my.board.webpath}")
	private String webPath; // 웹 이미지 요청 경로
	
	@Override
	public int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		
		int result = mapper.insertBoard(board);
		
		if(result == 0) return 0;
		
		int boardNo = board.getBoardNo();
		
		List<BoardImg> uploadList = new ArrayList<>();
		
		
		for(int i = 0; i<images.size(); i++) {
			
			
			if(images.get(i).getSize() > 0) {
				
				BoardImg img = new BoardImg();
				
				img.setBoardNo(boardNo); 
				img.setBoardImageOrder(i); 	 
				
				
				img.setBoardImageOriginalName(images.get(i).getOriginalFilename()); 
				
				//웹 접근 경로
				img.setBoardImagePath(webPath);
			
				//변경된 파일명
				img.setBoardImageRename(Util.fileRename(images.get(i).getOriginalFilename())); 
				
				//실제 업로드된 파일을 img에 세팅
				img.setUploadFile(images.get(i));
				
				//uploadList에 추가
				uploadList.add(img);
			}
		}
		
		if(uploadList.isEmpty()) {
			return boardNo;
		}
		
		result = mapper.insertUploadList(uploadList);
		
		
		if(result == uploadList.size()) {
			
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getBoardImageRename()));
			}
		}else { 
			
			throw new BoardWriteException("파일 정보 DB 삽입 실패");
		}
		
		return boardNo;
		
	}
	
	@Override
	public int deleteBoard(int boardNo) {
		return mapper.deleteBoard(boardNo);
	}
	
	@Override
	public Board updateBoardDetail(int boardNo) {
		return mapper.updateBoardDetail(boardNo);
	}
	
	@Override
	public int updateBoard(Board updateBoard) {
		
		return mapper.updateBoard(updateBoard);
	}
	
	
	
}
