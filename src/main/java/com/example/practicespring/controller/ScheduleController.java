package com.example.practicespring.controller;

import com.example.practicespring.dto.ScheduleRequestDto;
import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    // ScheduleService 의존성주입
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 데이터삽입
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto) , HttpStatus.CREATED); // 이 형태로 응답이 된거임!!!
    }

    // 전체조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {

        return new ResponseEntity<>(scheduleService.findAllSchedules() , HttpStatus.OK);
    }

    // 단건조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {

        return new ResponseEntity<>(scheduleService.findScheduleById(scheduleId) , HttpStatus.OK);
    }

    // 아이디를 통한 단건수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.scheduleUpdate(scheduleId, dto.getName(), dto.getDescription(), dto.getPassword()) , HttpStatus.OK);
    }

    // 아이디를 통한 단건삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {
        scheduleService.deleteSchedule(scheduleId, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
