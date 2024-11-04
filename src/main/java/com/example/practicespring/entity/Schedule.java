package com.example.practicespring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    // 모든 데이터들.
    private Long scheduleId; // 데이터베이스에서 관리하는 고유식별자

    private String password;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Schedule(String password,String name,String description,Date createdAt,Date updatedAt) {
        this.password = password;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Schedule(long scheduleId, String name, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.scheduleId = scheduleId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateSchedule(String name, String description) {
        this.name = name;
        this.description = description;
        this.updatedAt = new Date(); // 업데이트 시간을 현재시간으로.
    }
}
