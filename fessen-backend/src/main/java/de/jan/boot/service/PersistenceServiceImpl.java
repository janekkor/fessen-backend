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
import de.jan.boot.repository.FamilyDao;
import de.jan.boot.repository.MealDao;
import de.jan.boot.repository.MemberDao;
import de.jan.boot.repository.ScheduleDao;

@Transactional
@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	private FamilyDao familyDao;	
	
	@Autowired
	private MealDao mealDao;
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<Meal> readAllMeals(String familyCode) {
		Family family = familyDao.findByCode(familyCode);
		return mealDao.findAllByFamily(family);
	}
	
	@Override
	public List<Member> readAllMembers(String familyCode) {
		Family family = familyDao.findByCode(familyCode);
		return memberDao.findAllByFamily(family);
	}
	
	@Override
	public Schedule retrieveScheduleForMemberAndDay(Member member, Date day) {
		return scheduleDao.findByMemberAndDay(member, day);
	}
	
	@Override
	public List<Schedule> readAllSchedulesForFamily(String familyCode) {
		return scheduleDao.findAllByFamily(familyCode);
	}
	
	@Override
	public Schedule saveSchedule(Schedule newSchedule) {
		Schedule dbSchedule = scheduleDao.findByMemberAndDay(newSchedule.getMember(), newSchedule.getDay());
		Date currentTime = new Date();
		
		if (dbSchedule != null) {
			dbSchedule.setMeal(newSchedule.getMeal());
			dbSchedule.setModTime(currentTime);
			return scheduleDao.save(dbSchedule);
		}
		
		newSchedule.setCreationTime(currentTime);
		newSchedule.setModTime(currentTime);
		return scheduleDao.save(newSchedule);
	}
	
	@Override
	public void deleteSchedule(Schedule newSchedule) {
		scheduleDao.delete(newSchedule);
	}	

	@Override
	public Schedule readScheduleForFamilyAndDay(String familyCode, Date day) {
		Schedule foundSchedule = scheduleDao.findByFamilyAndDay(familyCode, day);
		return foundSchedule;
	}

	@Override
	public Family readFamily(String familyCode) {
		return familyDao.findByCode(familyCode);
	}
}
