package com.example.practicespring.controller;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // JSON 으로 데이터 주고받기.
@RequestMapping("/api/schedules")
public class ScheduleController {
    // 키 , 벨류 값으로
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
    // Dto 응답.                 유저의 입력을 바로 매개변수로 받기 , 이 타입으로.
        // 식별자 1씩 증가.
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // 스케쥴 생성시 이름과 패스워드는 필수.
        if (dto.getName() == null || dto.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 요청받은 데이터로 스케쥴을 생성.
        Schedule schedule = new Schedule(scheduleId,dto.getPassword(), dto.getName(), dto.getDescription(), dto.getCreatedAt(),dto.getUpdatedAt());

        // 일단 맵에 자료 저장.
        scheduleList.put(scheduleId , schedule);

        // 리턴
        return new ResponseEntity<>(new ScheduleResponseDto(schedule) , HttpStatus.CREATED); // 이 형태로 응답이 된거임!!!
    }

    // 전체조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {

        // 리스트 초기화
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return new ResponseEntity<>(responseList , HttpStatus.OK);
    }

    // 특정 id 로 조회.
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {

        Schedule schedule = scheduleList.get(scheduleId);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule) , HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {

        Schedule schedule = scheduleList.get(scheduleId);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 이름과 패스워드는 필수
        if (dto.getPassword() == null || dto.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 오류
        if (!schedule.getPassword().equals(dto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        schedule.updateSchedule(dto);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule) , HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {

        Schedule schedule = scheduleList.get(scheduleId);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (schedule.getPassword().equals(dto.getPassword())) {
            scheduleList.remove(scheduleId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
