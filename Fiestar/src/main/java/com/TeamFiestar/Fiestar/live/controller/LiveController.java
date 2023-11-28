package com.TeamFiestar.Fiestar.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.live.agora.media.RtcTokenBuilder2;
import com.TeamFiestar.Fiestar.live.agora.media.RtcTokenBuilder2.Role;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LiveController {
	
	// 토큰 생성
    @GetMapping("/generateToken")
    @ResponseBody
    public String generateAgoraToken(
            @RequestParam(value="flag", required = false, defaultValue = "1") int flag
    ) {
    	
    	RtcTokenBuilder2 tokenBuilder = new RtcTokenBuilder2();
    	Role rtcRole = null;
    	if (flag == 1) {
    		rtcRole = Role.ROLE_PUBLISHER;
		}else {
			rtcRole = Role.ROLE_SUBSCRIBER;
		}
    	

        String appId = "8db96ea10154462a8fcb52cee3d45ccb";
        String appCertificate = "d9b7838da2cb4bd0b3d9a9aa1c82eba6";
        int expirationTimeInSeconds = 36000;
        int uid = 2;
        String channelName = "kang2";
        

        String token = tokenBuilder.buildTokenWithUid(
                appId,
                appCertificate,
                channelName,
                uid,
                rtcRole,
                expirationTimeInSeconds, expirationTimeInSeconds
        );

        log.debug("token = " + token);
        return token;
    }
	
}
