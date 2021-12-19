package de.jan.boot.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jan.boot.model.Family;
import de.jan.boot.model.Meal;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;
import de.jan.boot.repository.MealDao;
import de.jan.boot.repository.MemberDao;
import de.jan.boot.repository.ScheduleDao;

@Transactional
@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	private MealDao mealDao;
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<Meal> readAllMeals(Family family) {
		return mealDao.findAllByFamily(family);
	}
	
	@Override
	public List<Member> readAllMembers(Family family) {
		return memberDao.findAllByFamily(family);
	}
	
	@Override
	public Schedule retrieveScheduleForMemberAndDay(Member member, Date day) {
		return scheduleDao.findAllByMemberAndDay(member, day);
	}
	
	@Override
	public List<Schedule> readAllSchedulesForFamily(Family family) {
		return scheduleDao.findAllByFamily(family);
	}
	
	@Override
	public Schedule saveSchedule(Schedule newSchedule) {
		Schedule dbSchedule = scheduleDao.findAllByMemberAndDay(newSchedule.getMember(), newSchedule.getDay());
		
		if (dbSchedule != null) {
			dbSchedule.setMeal(newSchedule.getMeal());
			return scheduleDao.save(dbSchedule);
		}
		
		return scheduleDao.save(newSchedule);
	}
}
