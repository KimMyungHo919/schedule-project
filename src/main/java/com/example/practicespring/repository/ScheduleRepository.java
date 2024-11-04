package com.example.practicespring.repository;

import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule saveSchdule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Schedule findScheduleById(Long scheduleId);

    void deleteSchedule(Long scheduleId);
}
