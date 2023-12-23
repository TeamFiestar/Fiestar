package com.TeamFiestar.Fiestar.media.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.media.model.dto.Media;

public interface MediaService {

	/** 미디어 리스트 조회
	 * @param map
	 * @param cp 
	 * @return
	 */
	Map<String, Object> selectMediaList(Map<String, Object> map, int cp);

	/** 미디어 삽입
	 * @param inserMedia
	 * @return
	 */
	int insertMedia(Media inserMedia);

	/** 미디어 디테일 조회
	 * @param mediaNo
	 * @param memberNo 
	 * @return
	 */
	Media mediaDetail(Map<String, Object> map);

	Media updateMediaDetail(int mediaNo);

	int updateMedia(Media updateMedia);

	int deleteMedia(int mediaNo);

	int updateReadCount(int mediaNo);

	int selectArtistAdminNo(String artistGroupTitle);

}
