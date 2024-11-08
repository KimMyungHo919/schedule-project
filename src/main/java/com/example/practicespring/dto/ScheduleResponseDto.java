package com.example.practicespring.dto;

import com.example.practicespring.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

// 응답데이터 Dto
@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId; // 데이터베이스에서 관리하는 고유식별자

    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    // 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.name = schedule.getName();
        this.description = schedule.getDescription();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
