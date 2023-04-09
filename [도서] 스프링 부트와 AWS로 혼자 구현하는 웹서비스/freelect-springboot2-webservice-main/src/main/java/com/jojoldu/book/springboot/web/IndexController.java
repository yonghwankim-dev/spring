package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @Autowired
    public IndexController(PostService postService, HttpSession httpSession) {
        this.postService = postService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String saveForm() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "posts-update";
    }
}
