package com.example.resolvelostupdate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int viewCount;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.viewCount = 0;
    }

    public void view() {
        viewCount++;
    }
}
