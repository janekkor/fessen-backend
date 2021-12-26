package de.jan.boot.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jan.boot.bean.FamilyDate;
import de.jan.boot.bean.MyPair;
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

	@RequestMapping("/familyschedules")
	public List<Schedule> retrieveSchedules(@RequestBody String familyCode) {
		return persistenceService.readAllSchedulesForFamily(familyCode);
	}
	
	
	@PostMapping("/familydateschedule")
	public Schedule retrieveScheduleForFamilyAndDate(@RequestBody FamilyDate familyDate) {
		System.out.println(familyDate);
		Schedule scheduleFromDB = persistenceService.readScheduleForFamilyAndDay(familyDate.getFamilyCode(), familyDate.getDay());
		System.out.println(scheduleFromDB);
		return scheduleFromDB;
	}
	
	@RequestMapping(value = "/saveschedule", method = RequestMethod.POST)
	ResponseEntity<?> newSchedule(@RequestBody Schedule schedule) {
		System.out.println("************* POST schedule auf der Serverseite. Received schedule with id: " + schedule.getId() + " *************************");
		Schedule savedSchedule = persistenceService.saveSchedule(schedule);
		return new ResponseEntity<Schedule>(savedSchedule, HttpStatus.CREATED);
	}
	
	//Test mappings
	@RequestMapping("/test")
	public String test( ) {
		return "This is a test reply from FessenController on the backend side of this application.";
	}

	@PostMapping("/jsonbody")
	public void greetingJson(HttpEntity<String> httpEntity) {
	    String json = httpEntity.getBody();
	    System.out.println(json);
	    System.out.println("JSON END!!!");
	}

	@PostMapping("/mypair")
	public MyPair mypairJson(@RequestBody MyPair myPair) {
		
		System.out.println(myPair);
		System.out.println("MyPair JSON END!!!");
		
		myPair.setValue1(myPair.getValue1() + "X");
		myPair.setValue2(myPair.getValue2() + "X");
		
		return myPair;
	}
}
