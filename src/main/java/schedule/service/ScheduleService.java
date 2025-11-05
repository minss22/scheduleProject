package schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.*;
import schedule.entity.Schedule;
import schedule.repository.ScheduleRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // Lv 1. 일정 생성
    @Transactional
    public ScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request); // DTO를 Schedule로 변경
        Schedule saved = scheduleRepository.save(schedule); // 일정 저장

        return new ScheduleResponse(saved); // 다시 DTO로 변경하여 리턴
    }

    // Lv 2. 일정 조회 - 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll(String name) {
        // 전체 일정 조회하고, '수정일' 기준 내림차순으로 정렬
        List<Schedule> schedules = name==null // name이 null이면 전체 조회, 아니면 '작성자명'을 기준으로 조회
                ? scheduleRepository.findAllByOrderByModifiedAtDesc()
                : scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);

        return schedules.stream().map(ScheduleResponse::new).toList(); // List<ScheduleResponse>로 변경하여 리턴
    }

    // Lv 2. 일정 조회 - 선택 일정 조회
    @Transactional(readOnly = true)
    public ScheduleResponse getOne(Long id) {
        Schedule schedule = findOrThrow(id); // 일정 조회

        return new ScheduleResponse(schedule);
    }

    // Lv 3. 일정 수정
    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest request) {
        // 'id'를 기준으로 조회, null이면 예외 처리
        Schedule schedule = findOrThrow(id); // 일정 조회
        checkPassword(schedule.getPassword(), request.getPassword()); // 비밀번호 확인

        // 선택한 일정에서 '일정 제목', '작성자명'만 수정 가능
        schedule.update(request.getTitle(), request.getName());
        scheduleRepository.flush(); // 변경내용 반영

        return new ScheduleResponse(schedule);
    }

    // Lv 4. 일정 삭제
    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {
        Schedule schedule = findOrThrow(id); // 일정 조회
        checkPassword(schedule.getPassword(), request.getPassword()); // 비밀번호 확인

        scheduleRepository.deleteById(id);
    }

    // 'id'를 기준으로 조회, null이면 예외 처리
    private Schedule findOrThrow(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
    }

    private void checkPassword(String savedPw, String requestPw) {
        if (!savedPw.equals(requestPw)) // 비밀번호 다르면 예외 처리
            throw new IllegalStateException("비밀번호를 틀렸습니다.");
    }
}
