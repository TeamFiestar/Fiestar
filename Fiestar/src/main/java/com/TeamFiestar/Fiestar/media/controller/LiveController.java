package com.TeamFiestar.Fiestar.media.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.media.agora.media.RtcTokenBuilder2;
import com.TeamFiestar.Fiestar.media.agora.media.RtcTokenBuilder2.Role;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("media")
public class LiveController {
	
	@GetMapping("live")
	public String moveLive() {
		return "media/media";
	}
	
	@ResponseBody
	@GetMapping("generateToken")
	public String generateAgoraToken(@RequestParam("roleFlag") int roleFlag,
			@RequestParam(name = "channelName", required = false, defaultValue = "kang") String channelName
			
			) {
    	
    	RtcTokenBuilder2 tokenBuilder = new RtcTokenBuilder2();
    	Role rtcRole = Role.ROLE_PUBLISHER;
    	if (roleFlag != 1) {
			rtcRole = Role.ROLE_SUBSCRIBER;
		}
    	

        String appId = "8db96ea10154462a8fcb52cee3d45ccb";
        String appCertificate = "d9b7838da2cb4bd0b3d9a9aa1c82eba6";
        int expirationTimeInSeconds = 36000;
        int uid = 0;

        String token = tokenBuilder.buildTokenWithUid(
                appId,
                appCertificate,
                channelName,
                uid,
                rtcRole,
                expirationTimeInSeconds, expirationTimeInSeconds
        );

        return token;
    }
	
}
