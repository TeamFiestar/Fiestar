package com.TeamFiestar.Fiestar.board.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.board.model.dto.Board;

public interface EditBoardService {

	int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException;

	int deleteBoard(int boardNo);

	int updateBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException;

	Board updateBoardDetail(int boardNo);

}
