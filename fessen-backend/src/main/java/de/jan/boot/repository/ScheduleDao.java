package de.jan.boot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Family;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {

	Schedule findAllByMemberAndDay(Member member, Date day);

	List<Schedule> findAllByFamily(Family family);
	
}
