package de.jan.boot.service;

import java.util.Date;
import java.util.List;

import de.jan.boot.model.Family;
import de.jan.boot.model.Meal;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;

public interface PersistenceService {
	
	List<Meal> readAllMeals(String familyCode);
	
	List<Member> readAllMembers(String familyCode);

	Schedule retrieveScheduleForMemberAndDay(Member member, Date day);
	
	List<Schedule> readAllSchedulesForFamily(String familyCode);
	
	Schedule saveSchedule(Schedule newSchedule);

	Schedule readScheduleForFamilyAndDay(String familyCode, Date day);
}
