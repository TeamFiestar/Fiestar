package com.TeamFiestar.Fiestar.common.scheduling;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TeamFiestar.Fiestar.scheduling.service.SchedulingService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@PropertySource("classpath:/config.properties")
public class ImageDeleteScheduling {

	@Autowired
	private SchedulingService service;
	
	@Value("{artist.image.location}")
	private String imageLocation;
	@Value("{artist.profile.location}")
	private String profileLocation;
	@Value("{artist.backImg.location}")
	private String backImgLocation;
	@Value("{artist.mainimg.location}")
	private String mainimgLocation;
	@Value("{artist.logoimg.location}")
	private String logoimgLocation;
	@Value("{artist.profileimg.location}")
	private String profileimgLocation;
	@Value("{my.shopContent.location}")
	private String shopContentLocation;
	@Value("{my.shopThumbnail.location}")
	private String shopThumnailLocation;
	@Value("{my.shop.location}")
	private String shopLocation;
	@Value("{my.member.location}")
	private String memberLocation;
	@Value("{my.board.location}")
	private String boardLocation;
	
	@Scheduled(cron = "0 * * * * *")
	
	public void schedule() {
		log.info("스케줄러 동작");
		
		File imageFolder = new File(imageLocation);
		File profileFolder = new File(profileLocation);
		File backImgFolder = new File(backImgLocation);
		File mainimgFolder = new File(mainimgLocation);
		File logoimgFolder = new File(logoimgLocation);
		File profileimgFolder = new File(profileimgLocation);
		File shopContentFolder = new File(shopContentLocation);
		File shopThumnailFolder = new File(shopThumnailLocation);
		File shopFolder = new File(shopLocation);
		File memberFolder = new File(memberLocation);
		File boardFolder = new File(boardLocation);
		
		File[] imageArr = imageFolder.listFiles();
		File[] profileArr = profileFolder.listFiles();
		File[] backImgArr = backImgFolder.listFiles();
		File[] mainimgArr = mainimgFolder.listFiles();
		File[] logoimgArr = logoimgFolder.listFiles();
		File[] profileimgArr = profileimgFolder.listFiles();
		File[] shopContentArr = shopContentFolder.listFiles();
		File[] shopThumnailArr = shopThumnailFolder.listFiles();
		File[] shopArr = shopFolder.listFiles();
		File[] memberArr = memberFolder.listFiles();
		File[] boardArr = boardFolder.listFiles();
		
		File[] imgArr = new File[imageArr.length + profileArr.length + backImgArr.length + 
		                           mainimgArr.length + logoimgArr.length + profileimgArr.length + 
		                           shopContentArr.length + shopThumnailArr.length + shopArr.length
		                           + memberArr.length + boardArr.length];
		
		System.arraycopy(imageArr, 0, imgArr, 0, imageArr.length);
		System.arraycopy(profileArr, 0, imgArr, imageArr.length, profileArr.length);
		System.arraycopy(backImgArr, 0, imgArr, profileArr.length, backImgArr.length);
		System.arraycopy(mainimgArr, 0, imgArr, backImgArr.length, mainimgArr.length);
		System.arraycopy(logoimgArr, 0, imgArr, mainimgArr.length, logoimgArr.length);
		System.arraycopy(profileimgArr, 0, imgArr, logoimgArr.length, profileimgArr.length);
		System.arraycopy(shopContentArr, 0, imgArr, profileimgArr.length, shopContentArr.length);
		System.arraycopy(shopThumnailArr, 0, imgArr, shopContentArr.length, shopThumnailArr.length);
		System.arraycopy(shopArr, 0, imgArr, shopThumnailArr.length, shopArr.length);
		System.arraycopy(memberArr, 0, imgArr, shopArr.length, memberArr.length);
		System.arraycopy(boardArr, 0, imgArr, memberArr.length, boardArr.length);
		
		
		List<File> serverImageList = Arrays.asList(imgArr);
		
		Map<String, Object> map = service.selectDbImageList();
	}
	
}
