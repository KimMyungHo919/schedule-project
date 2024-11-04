package com.example.practicespring.repository;

import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @Override
    public Schedule saveSchdule(Schedule schedule) {
        // 데이터베이스와 상호작용
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        schedule.setScheduleId(scheduleId);

        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = new ArrayList<>();

        for(Schedule schedule : scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            allSchedules.add(responseDto);
        }

        return allSchedules;
    }

    @Override
    public Schedule findScheduleById(Long scheduleId) {
        return scheduleList.get(scheduleId);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        scheduleList.remove(scheduleId);
    }
}
