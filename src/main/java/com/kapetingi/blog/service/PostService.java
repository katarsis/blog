package com.kapetingi.blog.service;

import com.kapetingi.blog.entities.Post;
import com.kapetingi.blog.entities.User;
import com.kapetingi.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void insert(Post post) {
        if(post.getDateCreated() == null){
            post.setDateCreated(LocalDateTime.now());
        }
        postRepository.save(post);
    }

    public List<Post> findByUser(User user) {
     return postRepository.findByCreatorId(user.getId());
    }

    public Post getPost(Long id) {
        return postRepository.findOne(id);
    }

    public boolean deletePost(Long id) {
        Post thePost = postRepository.findOne(id);
        if(thePost == null)
            return false;
        postRepository.delete(id);
        return true;
    }

    public Post find(Long postId) {
        return postRepository.findOne(postId);
    }
}
