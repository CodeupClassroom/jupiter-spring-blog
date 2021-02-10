package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HowdyController {

	@GetMapping("/hi")
	public String helloView(){
		//This will return a file named howdy.html that is inside of resources/templates
		return "howdy";
	}

	@GetMapping("/hi/{name}")
	public String helloNameView(@PathVariable String name, Model model){
		//This will take the name from the url parameter and add it to the model as an attribute.
		// This allows for us to dynamically add items to our view
		//The Model object is different from our models that represent the DB
		model.addAttribute("name", name);
		return "howdy";
	}


	// if and each tags
	@GetMapping("/all/{willSayHi}")
	public String allHello(@PathVariable boolean willSayHi, Model model) {

		List<String> names = new ArrayList<>();

		names.add("Fred");
		names.add("John");
		names.add("Lisa");

		model.addAttribute("names", names);
		model.addAttribute("willSayHi", willSayHi);

		return "howdy";

	}

	@GetMapping("/join")
	public String showJoinForm() {
		return "join";
	}

	@PostMapping("/join")
	public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
		model.addAttribute("cohort", "Welcome to " + cohort + "!");
		return "join";
	}
}
