package com.example.practicespring.service;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long scheduleId);

    ScheduleResponseDto scheduleUpdate(Long scheduleId , String name, String description);

    void deleteSchedule(Long scheduleId);
}
