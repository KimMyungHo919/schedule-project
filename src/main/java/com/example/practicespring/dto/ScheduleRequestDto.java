package com.example.practicespring.dto;

import lombok.Getter;

import java.util.Date;

@Getter // 값을 쉽게 가져올수있게?
public class ScheduleRequestDto {
    // 내가 요청받을 정보는 "이것" 입니다!

    private String password;
    private String name;
    private String description;
    private Date createdAt = new Date(); // 요청받을때 현재시간으로.
    private Date updatedAt = new Date(); // 요청받을때 현재시간으로.
}
