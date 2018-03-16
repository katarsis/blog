package com.kapetingi.blog.controllers;

import com.kapetingi.blog.config.CustomUserDetails;
import com.kapetingi.blog.entities.Post;
import com.kapetingi.blog.service.PostService;
import com.kapetingi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/posts")
    public List<Post> posts(){
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public String publishPost(@RequestBody Post post){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setCreator(userService.getUser(userDetails.getUsername()));
        postService.insert(post);
        return "Post published";
    }

    @GetMapping(value = "/posts/{username}")
    public List<Post> postByUserName(@PathVariable String username){
        return postService.findByUser(userService.getUser(username));
    }

}
