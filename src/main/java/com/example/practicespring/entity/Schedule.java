package com.example.practicespring.entity;

import com.example.practicespring.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public void updateSchedule(ScheduleRequestDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.updatedAt = new Date(); // 업데이트 시간을 현재시간으로.
    }
}
