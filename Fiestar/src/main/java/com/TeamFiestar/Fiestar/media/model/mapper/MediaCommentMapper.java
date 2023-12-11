package com.TeamFiestar.Fiestar.media.model.mapper;

import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;

@Mapper
public interface MediaCommentMapper {

	int inputComment(MediaComment inputComment);

	List<Comment> selectComment(Map<String, Integer> map);

	int deleteComment(int commentNo);

	int insertLike(MediaComment comment);

	int deleteLike(MediaComment comment);

}
