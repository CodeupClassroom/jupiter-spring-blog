package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads/jpa")
    @ResponseBody
    public List<Ad> jpaIndex() {
        return adsDao.findAll();
    }

    @GetMapping("/ads/jpa/{id}")
    @ResponseBody
    public String viewJpaAd(@PathVariable long id) {
        return adsDao.getOne(id).toString();
    }

    @GetMapping("ads/{id}")
    public String viewAd(@PathVariable long id, Model model) {
        Ad myAd = adsDao.getOne(id);
        model.addAttribute("ad", myAd);

        return "ads/show";
    }

    @GetMapping("/ads/jpa/create")
    public void createAd() {
        Ad ad = new Ad();
        ad.setTitle("Yellow Submarine");
        ad.setDescription("We all live in a yellow submarine");

        adsDao.save(ad);
    }

    @GetMapping("/ads/jpa/delete")
    public void deleteAd() {
        adsDao.deleteById(6L);
    }

    @GetMapping("/ads/search")
    @ResponseBody
    public Ad returnAdByTitle() {
        return adsDao.findByTitle("Yellow Submarine");
    }

    @GetMapping("/ads/order")
    @ResponseBody
    public List<Ad> returnAdsByTitle() {
        return adsDao.findByOrderByTitle();
    }
}
