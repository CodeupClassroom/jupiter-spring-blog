package com.codeup.springblog;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PostStartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PasswordEncoder encoder;

    public PostStartupRunner(UserRepository userDao, PostRepository postDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // don't run if there's already any users in the database
        if (userDao.count() != 0) {
            return;
        }
        User user = new User();
        user.setUsername("cody");
        user.setEmail("cody@codeup.com");
        user.setPassword("codeuprocks");
        userDao.save(user);

        Post post = new Post();
        post.setTitle("Demo title");
        post.setBody("Demo body");
        post.setUser(user);
        postDao.save(post);
    }
}
