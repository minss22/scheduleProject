package schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import schedule.dto.CreateScheduleRequest;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title; // 일정 제목
    @Column(nullable = false)
    private String content; // 일정 내용
    @Column(length = 50, nullable = false)
    private String name; // 작성자
    @Column(length = 50, nullable = false)
    private String password; // 비밀번호

    public Schedule(CreateScheduleRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.name = request.getName();
        this.password = request.getPassword();
    }

    public void update(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
