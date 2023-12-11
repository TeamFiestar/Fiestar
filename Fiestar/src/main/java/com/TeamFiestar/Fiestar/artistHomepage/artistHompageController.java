package com.TeamFiestar.Fiestar.artistHomepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class artistHompageController {
	
	
	@GetMapping("artistprofile/profile")
	public String artistProfile() {
		return "artistprofile/profile";
	}
}
