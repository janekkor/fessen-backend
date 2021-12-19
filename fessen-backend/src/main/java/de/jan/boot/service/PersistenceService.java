package de.jan.boot.service;

import java.util.Date;
import java.util.List;

import de.jan.boot.model.Family;
import de.jan.boot.model.Meal;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;

public interface PersistenceService {
	
	List<Meal> readAllMeals(Family family);
	
	List<Member> readAllMembers(Family family);

	Schedule retrieveScheduleForMemberAndDay(Member member, Date day);
	
	List<Schedule> readAllSchedulesForFamily(Family family);
	
	Schedule saveSchedule(Schedule newSchedule);
}
