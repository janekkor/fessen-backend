package de.jan.boot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.jan.boot.model.Family;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {

	Schedule findByMemberAndDay(Member member, Date day);
	//CAST(s.day AS date) = CAST(:#{#day} AS date) compares only the date part (without the time part)
	@Query("select s from Schedule s join s.member m join m.family f where f.code = :#{#familyCode} and CAST(s.day AS date) = CAST(:#{#day} AS date)")
	Schedule findByFamilyAndDay(String familyCode, Date day);	

	@Query("select s from Schedule s join s.member m join m.family f where f.code = :#{#familyCode}")
	List<Schedule> findAllByFamily(String familyCode);
	
}
