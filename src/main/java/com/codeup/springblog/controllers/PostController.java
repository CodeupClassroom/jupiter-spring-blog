package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final UserService userService;

    public PostController(PostRepository postsDao, UserRepository usersDao, UserService userService){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {

        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id) {
//        get single post by id later
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        //TODO: Change user to logged in user dynamic
        User user = userService.getLoggedInUser();
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        System.out.println("Deleting post...");
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        // Will throw if no users in the db!
        // In the future, we will get the logged in user
        User user = userService.getLoggedInUser();
        post.setUser(user);

        postsDao.save(post);
        return "redirect:/posts";
    }
}
