package de.jan.boot.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import de.jan.boot.bean.FamilyDate;
import de.jan.boot.bean.MyPair;
import de.jan.boot.model.Family;
import de.jan.boot.model.Meal;
import de.jan.boot.model.Member;
import de.jan.boot.model.Schedule;
import de.jan.boot.service.PersistenceService;

@RestController
public class FessenController {
	
	Logger logger = LoggerFactory.getLogger(FessenController.class);

	@Autowired
	private PersistenceService persistenceService;
	
	
	@PostMapping("/familymeals")
	public List<Meal> retrieveAllMealsForFamily(@RequestBody String familyCode) {
		logger.debug("retrieveAllMealsForFamily has been called with the parameter familyCode: {0}", familyCode);
		return persistenceService.readAllMeals(familyCode);
	}
	
	@PostMapping("/familymembers")
	public List<Member> retrieveAllMembersForFamily(@RequestBody String familyCode) {
		logger.debug("retrieveAllMembersForFamily has been called with the parameter familyCode: {0}", familyCode);
		List<Member> allFamilyMembers = persistenceService.readAllMembers(familyCode);
		return allFamilyMembers;
	}

	@PostMapping("/familyschedules")
	public List<Schedule> retrieveSchedules(@RequestBody String familyCode) {
		logger.debug("retrieveSchedules has been called with the parameter familyCode: {0}", familyCode);
		return persistenceService.readAllSchedulesForFamily(familyCode);
	}
	
	
	@PostMapping("/familydateschedule")
	public Schedule retrieveScheduleForFamilyAndDate(@RequestBody FamilyDate familyDate) {
		logger.debug("retrieveScheduleForFamilyAndDate has been called with the parameter familyDate: {0}", familyDate);
		Schedule scheduleFromDB = persistenceService.readScheduleForFamilyAndDay(familyDate.getFamilyCode(), familyDate.getDay());
		return scheduleFromDB;
	}
	
	@PostMapping("/saveschedule")
	public Schedule newSchedule(@RequestBody Schedule schedule) {
		logger.debug("newSchedule has been called with the parameter schedule: {0}", schedule);
		schedule.setId(null);
		schedule.setModTime(new Date());
		Schedule savedSchedule = persistenceService.saveSchedule(schedule);
		return savedSchedule;
	}
	
	@PostMapping("/deleteschedule")
	public boolean deleteSchedule(@RequestBody Schedule schedule) {
		logger.debug("deleteSchedule has been called with the parameter schedule: {0}", schedule);
		persistenceService.deleteSchedule(schedule);
		//true will be returned, if no exception occurs. Otherwise the "return true" will not be reached and executed  
		return true;
	}
	
	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestHeader("Authorization") String familyCodePass) {
		logger.debug("login has been called with the parameter familyCodePass: {0}", familyCodePass);
		System.out.println("Authorization: " + familyCodePass);
		StringTokenizer authorizationTokenizer = new StringTokenizer(familyCodePass);
		authorizationTokenizer.nextToken();  //Returns "Basic"
		String familyCodePassExtracted = authorizationTokenizer.nextToken();
		System.out.println("familyCodePass: " + familyCodePassExtracted);
		String familyCodePassDecoded = new String(Base64.getDecoder().decode(familyCodePassExtracted));
		System.out.println("familyCodePass decoded: " + familyCodePassDecoded);
		StringTokenizer familyCodePassTokenizer = new StringTokenizer(familyCodePassDecoded);
		String familyCode = familyCodePassTokenizer.nextToken(":");
		String password = familyCodePassTokenizer.nextToken(":");
		if (!loginOK(familyCode, password)) {
			throw new ResponseStatusException(
			           HttpStatus.FORBIDDEN, "User or password not correct!");
		}
		return ResponseEntity.ok("Authentication OK");
	}
	
	private boolean loginOK(String familyCode, String password) {
		
		try {
			Family familyDB = persistenceService.readFamily(familyCode);
			if (familyDB != null && familyDB.getPassword().equals(password)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	//Test mappings
	@GetMapping("/test")
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
