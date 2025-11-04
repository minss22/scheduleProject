package schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Schedule(String title, String content, String name, String password) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    public void update(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
