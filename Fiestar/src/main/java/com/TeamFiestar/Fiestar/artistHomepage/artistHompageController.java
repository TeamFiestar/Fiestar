package com.TeamFiestar.Fiestar.artistHomepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("{artistHomepage}")
public class artistHompageController {
	
	
	@GetMapping("memberFeed")
	public String memberFeed() {
		return "artistHomepage/memberFeed";
	}
}
