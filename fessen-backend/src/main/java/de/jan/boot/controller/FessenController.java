package de.jan.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jan.boot.model.Meal;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;
import de.jan.boot.service.PersistenceService;

@RestController
public class FessenController {

	@Autowired
	private PersistenceService persistenceService;
	
	
	@RequestMapping("/familymeals")
	public List<Meal> retrieveAllMealsForFamily(@RequestBody String familyCode) {
		return persistenceService.readAllMeals(familyCode);
	}
	
	@RequestMapping("/familymembers")
	public List<Member> retrieveAllMembersForFamily(@RequestBody String familyCode) {
		List<Member> allFamilyMembers = persistenceService.readAllMembers(familyCode);
		return allFamilyMembers;
	}

	@RequestMapping("/schedules")
	public List<Schedule> retrieveSchedules(@RequestBody String familyCode) {
		return persistenceService.readAllSchedulesForFamily(familyCode);
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	ResponseEntity<?> newSchedule(@RequestBody Schedule schedule) {
		
		System.out.println("************* POST schedule auf der Serverseite. Received schedule with id: " + schedule.getId() + " *************************");
		Schedule savedSchedule = persistenceService.saveSchedule(schedule);
		return new ResponseEntity<Schedule>(savedSchedule, HttpStatus.CREATED);
	}
	
	//Test mapping
	@RequestMapping("/test")
	public String test( ) {
		return "This is a test reply from FessenController on the backend side of this application.";
	}
	
}
