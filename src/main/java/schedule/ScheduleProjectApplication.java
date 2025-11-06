package schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main 클래스
 * - 프로그램 시작점
 */
@EnableJpaAuditing // Auditing 인프라 활성화
@SpringBootApplication
public class ScheduleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectApplication.class, args);
    }

}
