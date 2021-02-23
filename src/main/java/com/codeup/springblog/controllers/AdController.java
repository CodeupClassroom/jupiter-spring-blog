package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdController {
	private final AdRepository adsDao;
	private final UserRepository usersDao;

	public AdController(AdRepository adsDao, UserRepository usersDao) {
		this.adsDao = adsDao;
		this.usersDao = usersDao;
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
		User user = usersDao.findAll().get(0);
		ad.setUser(user);

		Ad savedAd = adsDao.save(ad);

		return "redirect:/ads";
	}
}
