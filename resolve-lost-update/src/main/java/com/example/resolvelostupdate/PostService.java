package com.example.resolvelostupdate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Long save(String title, String content) {
        Post post = postRepository.save(new Post(title, content));
        return post.getId();
    }

    public PostResponse find(Long postId) {
        Post post = findById(postId);
        post.view();
        return PostResponse.from(post);
    }

    private Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }
}
