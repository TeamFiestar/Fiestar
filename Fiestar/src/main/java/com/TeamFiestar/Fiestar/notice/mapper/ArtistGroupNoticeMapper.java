package com.TeamFiestar.Fiestar.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;

@Mapper
public interface ArtistGroupNoticeMapper {

	List<ArtistGroupNotice> notice(RowBounds rowBounds, int artistGroupNo);

	int countNoticeList(int artistGroupNo);

	List<ArtistGroupNotice> noticeDetail(Map<String, Object> map);

}
