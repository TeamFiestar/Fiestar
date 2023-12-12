package com.TeamFiestar.Fiestar.artistHomepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loginMember"})
public class artistHompageController {
	
	
	@GetMapping("artistprofile/profileModify")
	public String profileModify() {
		return "artistprofile/profileModify";
	}
	
	@GetMapping("artistprofile/profile")
	public String artistprofile() {
		return "artistprofile/profile";
	}
	
	
	
}
