package com.example.acceptancetestisolation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private int age;

    public Member(String name, int age) {
        this(null, name, age);
    }

    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void update(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
