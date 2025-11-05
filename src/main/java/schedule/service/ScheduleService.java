package schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.*;
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

        Schedule saved = scheduleRepository.save(schedule); // 일정 저장
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    // Lv 2. 일정 조회 - 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll(String name) {
        // 전체 일정 조회하고, '수정일' 기준 내림차순으로 정렬
        List<Schedule> schedules = name==null // name이 null이면 전체 조회, 아니면 '작성자명'을 기준으로 조회
                ? scheduleRepository.findAllByOrderByModifiedAtDesc()
                : scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        List<GetOneScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }
        return dtos;
    }

    // Lv 2. 일정 조회 - 선택 일정 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long id) {
        // 'id'를 기준으로 조회, null이면 예외 처리
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // Lv 3. 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        // 'id'를 기준으로 조회, null이면 예외 처리
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        if (!request.getPassword().equals(schedule.getPassword())) // 비밀번호 다르면 예외 처리
            throw new IllegalStateException("비밀번호를 틀렸습니다.");

        schedule.update( // 선택한 일정 내용 중 '일정 제목', '작성자명'만 수정 가능
                request.getTitle(),
                request.getName()
        );

        scheduleRepository.flush(); // 변경내용 반영

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // Lv 4. 일정 삭제
    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        if (!request.getPassword().equals(schedule.getPassword())) // 비밀번호 다르면 예외 처리
            throw new IllegalStateException("비밀번호를 틀렸습니다.");

        scheduleRepository.deleteById(id);
    }
}
