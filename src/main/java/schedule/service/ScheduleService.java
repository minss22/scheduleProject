package schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.CreateScheduleRequest;
import schedule.dto.CreateScheduleResponse;
import schedule.dto.GetOneScheduleResponse;
import schedule.entity.Schedule;
import schedule.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // Lv 1. 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );

        Schedule saved = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }
}
