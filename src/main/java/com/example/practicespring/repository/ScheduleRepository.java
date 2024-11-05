package com.example.practicespring.repository;

import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchdule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Optional<Schedule> findScheduleById(Long scheduleId);

    int updateSchedule(Long scheduleId, String name, String description, String password);

    int deleteSchedule(Long scheduleId, String password);

    Schedule findScheduleByIdOrElseThrow(Long scheduleId);
}
