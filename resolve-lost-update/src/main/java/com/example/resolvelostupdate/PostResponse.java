package com.example.resolvelostupdate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long id;

    private String title;

    private String content;

    private int viewCount;

    public PostResponse(Long id, String title, String content, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getViewCount());
    }
}
