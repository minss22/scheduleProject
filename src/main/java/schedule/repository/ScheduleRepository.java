package schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
