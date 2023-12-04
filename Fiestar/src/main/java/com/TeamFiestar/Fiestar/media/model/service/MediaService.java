package com.TeamFiestar.Fiestar.media.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.media.model.dto.Media;

public interface MediaService {

	List<Media> selectMediaList(Map<String, Object> map);

}
