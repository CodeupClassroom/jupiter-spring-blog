package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class DiceController {
	@GetMapping("/roll-dice/{guess}")
	public String rollDice(@PathVariable int guess, Model model){
		String message;
//		https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
		int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);

		if(guess == randomNum){
			message = "Yay, you guessed correctly!";
		} else {
			message = "Sorry, try again!";
		}

		model.addAttribute("randomNumber", randomNum);
		model.addAttribute("guess", guess);
		model.addAttribute("message", message);

		return "roll-results";


	}

	@GetMapping("roll-dice")
	public String showRollDice(){
		return "roll-dice";
	}
}
