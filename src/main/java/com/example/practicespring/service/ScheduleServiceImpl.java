package com.example.practicespring.service;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;
import com.example.practicespring.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    // 레퍼지토리 의존성주입
    private final ScheduleRepository scheduleRepository;
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 저장
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        // 필수값 검증
        if (dto.getName() == null || dto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목 혹은 패스워드를 확인해주세요.");
        }

        Schedule schedule = new Schedule(dto.getPassword(), dto.getName(), dto.getDescription());

        return scheduleRepository.saveSchdule(schedule);
    }

    // 전체조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    // 단건조회
    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(schedule);
    }

    // 단건수정
    @Transactional
    @Override
    public ScheduleResponseDto scheduleUpdate(Long scheduleId, String name, String description, String password) {

        if (name == null || description == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "이름,내용을 찾을수 없습니다 : " + name + description);
        }

        int updatedRow = scheduleRepository.updateSchedule(scheduleId, name, description, password);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "아이디 혹은 비밀번호가 틀렸습니다 : " + scheduleId);
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(schedule);
    }

    // 단건삭제
    @Override
    public void deleteSchedule(Long scheduleId, String password) {

        int deletedRow = scheduleRepository.deleteSchedule(scheduleId, password);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "아이디 혹은 비밀번호를 확인해주세요." + scheduleId);
        }
    }
}
