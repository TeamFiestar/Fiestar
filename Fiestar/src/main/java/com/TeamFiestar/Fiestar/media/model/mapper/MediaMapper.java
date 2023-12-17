package com.TeamFiestar.Fiestar.media.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.media.model.dto.Media;

@Mapper
public interface MediaMapper {

	List<Media> selectMediaList(Map<String, Object> map);

	/** 미디어 삽입
	 * @param inserMedia
	 * @return
	 */
	int insertMedia(Media inserMedia);

	/** 미디어 상세 조회
	 * @param map
	 * @return
	 */
	Media mediaDetail(Map<String, Object> map);

}
