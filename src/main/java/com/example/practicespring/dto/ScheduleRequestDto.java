package com.example.practicespring.dto;

import lombok.Getter;

import java.util.Date;

// 요청데이터 Dto
@Getter
public class ScheduleRequestDto {

    private String password;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
