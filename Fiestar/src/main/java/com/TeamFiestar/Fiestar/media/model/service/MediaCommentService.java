package com.TeamFiestar.Fiestar.media.model.service;

import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;

public interface MediaCommentService {

	/** 댓글 입력
	 * @param inputComment
	 * @return
	 */
	int inputComment(MediaComment inputComment);

	List<Comment> selectComment(Map<String, Integer> map);

	int deleteComment(int commentNo);

	int insertLike(MediaComment comment);

	int deleteLike(MediaComment comment);

}
