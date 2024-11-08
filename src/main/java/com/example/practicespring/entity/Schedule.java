package com.example.practicespring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

// Entity
@Getter
@AllArgsConstructor
public class Schedule {

    private Long scheduleId; // 데이터베이스에서 관리하는 고유식별자

    private String password;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    // 생성자
    public Schedule(String password,String name,String description) {
        this.password = password;
        this.name = name;
        this.description = description;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
