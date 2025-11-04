package schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.entity.Schedule;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 전체 조회, 수정일 기준으로 내림차순
    List<Schedule> findAllByOrderByModifiedAtDesc();
    // 이름 기준으로 다건 조회, 수정일 기준으로 내림차순
    List<Schedule> findAllByNameOrderByModifiedAtDesc(String name);

}
