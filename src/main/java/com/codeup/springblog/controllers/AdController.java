package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdController {

	private final AdRepository adsDao;
	private final UserService userService;
	private final EmailService emailService;

	public AdController(AdRepository adsDao, UserService userService, EmailService emailService) {
		this.adsDao = adsDao;
		this.userService = userService;
		this.emailService = emailService;
	}

	@GetMapping("/ads")
	public String adsIndex(Model model) {

		model.addAttribute("ads", adsDao.findAll());
		return "ads/index";
	}

	@GetMapping("/ads/create")
	public String showAdForm(Model model){
		model.addAttribute("ad", new Ad());
		return "ads/create";
	}

	@PostMapping("/ads/create")
	public String createAd(@ModelAttribute Ad ad){
		//Later we will get logged in user
		User user = userService.loggedInUser();
		ad.setUser(user);

		Ad savedAd = adsDao.save(ad);

		//send an email once the ad is saved
		String subject = "New Ad Created: " + savedAd.getTitle();

		String body = "Dear " + savedAd.getUser().getUsername()
				+ ". Thank you for creating an ad. Your ad id is: "
				+ savedAd.getId();

		emailService.prepareAndSend(savedAd, subject, body);

		return "redirect:/ads";
	}
}
