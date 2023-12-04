package com.TeamFiestar.Fiestar.artistHomepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("artistHomepage")
public class artistHompageController {
	
	@GetMapping("feed")
	public String feed() {
		return "artistHomepage/feed";
	}
	@GetMapping("artist")
	public String artist() {
		return "artistHomepage/artist";
	}
	@GetMapping("memberFeed")
	public String memberFeed() {
		return "artistHomepage/memberFeed";
	}
}
