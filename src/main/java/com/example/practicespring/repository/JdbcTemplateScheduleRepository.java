package com.example.practicespring.repository;

import com.example.practicespring.dto.ScheduleResponseDto;
import com.example.practicespring.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 저장
    @Override
    public ScheduleResponseDto saveSchdule(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("scheduleId");

        Map<String , Object> parameters = new HashMap<>();
        parameters.put("name",schedule.getName());
        parameters.put("description",schedule.getDescription());
        parameters.put("password",schedule.getPassword());
        parameters.put("createdAt",schedule.getCreatedAt());
        parameters.put("updatedAt",schedule.getUpdatedAt());

        // 자동생성된 id 값을 number타입으로 반환.
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue() , schedule.getName(), schedule.getDescription() , schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    // 전체조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    // 단건조회
    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapperV2(), scheduleId);
        return result.stream().findAny();
    }

    @Override
    public int updateSchedule(Long scheduleId, String name, String description, String password) {
        return jdbcTemplate.update("update schedule set name = ?, description = ?, updatedAt = ? where scheduleId = ? and password = ?", name, description,new Date(), scheduleId, password);
    }

    // 삭제
    @Override
    public int deleteSchedule(Long scheduleId, String password) {
        return jdbcTemplate.update("delete from schedule where scheduleId = ? and password = ?", scheduleId, password);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapperV2() , scheduleId);
        return result.stream().findAny().orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디를 찾을수 없습니다 " + scheduleId));
    }




    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("scheduleId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        };

    }
}
