package schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
}
