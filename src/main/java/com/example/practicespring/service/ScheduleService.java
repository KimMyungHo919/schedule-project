package com.example.practicespring.service;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;

import java.util.List;

// 서비스 인터페이스
public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long scheduleId);

    ScheduleResponseDto scheduleUpdate(Long scheduleId , String name, String description, String password);

    void deleteSchedule(Long scheduleId, String password);
}
