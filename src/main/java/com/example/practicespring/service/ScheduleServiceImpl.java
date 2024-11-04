package com.example.practicespring.service;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;
import com.example.practicespring.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getPassword(), dto.getName(), dto.getDescription(), dto.getCreatedAt(), dto.getUpdatedAt());

        Schedule savedSchedule = scheduleRepository.saveSchdule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {

        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "아이디를 찾을수 없습니다 : " + scheduleId);
        }

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto scheduleUpdate(Long scheduleId, String name, String description) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "아이디를 찾을수 없습니다 : " + scheduleId);
        }
        if (name == null || description == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "이름,내용을 찾을수 없습니다 : " + name + description);
        }

        schedule.updateSchedule(name,description);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "아이디를 찾을수 없습니다 : " + scheduleId);
        }

        scheduleRepository.deleteSchedule(scheduleId);
    }
}
