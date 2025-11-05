package schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.dto.*;
import schedule.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        ScheduleResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedule(@RequestParam(required = false) String name) {
        List<ScheduleResponse> result = scheduleService.getAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules/{userId}")
    public ResponseEntity<ScheduleResponse> getOneSchedule(@PathVariable Long userId) {
        ScheduleResponse result = scheduleService.getOne(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{userId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long userId, @RequestBody UpdateScheduleRequest request) {
        ScheduleResponse result = scheduleService.update(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{userId}")
    public ResponseEntity<List<Void>> deleteSchedule(@PathVariable Long userId, @RequestBody DeleteScheduleRequest request) {
        scheduleService.delete(userId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}