package exgit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExController {
	
	@GetMapping("/ex")
	public String exHome(){
		return "exHome";
	}

}