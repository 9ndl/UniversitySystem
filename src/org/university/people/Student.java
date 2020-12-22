package org.university.people;

import org.university.software.*;
import org.university.hardware.*;

import java.io.Serializable;
import java.util.Collections; 

public class Student extends Person {

	private int ReqUnits; 
	private int CompletedUnits; 
	private int EnrolledOnlineCredits; 
	private int EnrolledCampusCredits; 
	private int TuitionFee;  
	private int OnlineFee; 
	private String name;
	private Department StudentDepartment;
	private String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri" }; 
	private String[] Time = {"8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm", "2:00pm to 3:15pm", "3:30pm to 4:45pm"}; 
	
//Constructors 
	public Student() {
	name = "unknown";
	ReqUnits = 0; 
	CompletedUnits = 0; 
	EnrolledOnlineCredits = 0; 
	EnrolledCampusCredits = 0; 
	TuitionFee = 0;  
	OnlineFee = 0;
	}
	
//Setters and Getters 
	public String getName(){
	return name;}

	public void setName(String aName){
	name = aName;}

	public Department getDepartment(){
	return StudentDepartment;	}

	public void setDepartment(Department aDepartmentName){
	StudentDepartment = aDepartmentName; }

	public void setCompletedUnits(int cunits) {
	 CompletedUnits = cunits; 	}
	
	public int requiredToGraduate() {
	  int UnitsNeeded = ReqUnits - CompletedUnits - EnrolledOnlineCredits - EnrolledCampusCredits; 
	  return UnitsNeeded; 	}
	

	public int getEnrolledOnlineCredits(){      //Online Credits 
	  return EnrolledOnlineCredits;	}
	
	public int getEnrolledCampusCredits(){      //In person credits 
	  return EnrolledCampusCredits;	}
		
	
	public void setTuitionFee(int Fee){
	  TuitionFee = Fee; }

	public int getTuitionFee(){
		return TuitionFee; }
	
	public void setRequiredCredits(int CR) {
		ReqUnits = CR;}
	
	//Incremental Methods and Calculations
	public void addToOnlineEnrolledCredits(OnlineCourse c1){
		 EnrolledOnlineCredits = (EnrolledOnlineCredits + c1.getCredits()); }
	
	public void addToCampusEnrolledCredits(CampusCourse c1){
		 EnrolledCampusCredits = (EnrolledCampusCredits + c1.getCredits()); }
	
	public void removeOnlineEnrolledCredits(OnlineCourse c1) {
		 EnrolledOnlineCredits = (EnrolledOnlineCredits - c1.getCredits()); }

	public void removeCampusEnrolledCredits(CampusCourse c1) {
		 EnrolledCampusCredits = (EnrolledCampusCredits - c1.getCredits()); }
	
	public void addToOnlineTuitionFee(OnlineCourse oc1) {          
		if(oc1.getCredits() == 3) {   
		OnlineFee = OnlineFee + 2000;
		TuitionFee = TuitionFee + 2000;}
		else if (oc1.getCredits() == 4) {
	    OnlineFee = OnlineFee + 3000; 
		TuitionFee = TuitionFee + 3000;}}
	
	public void addToPersonTuitionFee(CampusCourse c1) {
		TuitionFee = OnlineFee + (EnrolledCampusCredits * 300);}
	
	public void subFromOnlineTuitionFee(OnlineCourse oc1) {
		if(oc1.getCredits() == 3) { 
		OnlineFee = OnlineFee - 2000;
		TuitionFee = TuitionFee - 2000;}
		else if (oc1.getCredits() == 4) {
		OnlineFee = OnlineFee - 3000;
		TuitionFee = TuitionFee - 3000;}
	}
	
	
    public boolean ableToDrop(CampusCourse c1) {
    	boolean Drop = false; 
    	int A = (EnrolledCampusCredits - c1.getCredits()); 
    	if(A >= 6) {
    	 Drop = true; 
    	 return Drop;}
    	return Drop; }


	//Adding and Dropping Courses  
    
	public void addCourse(CampusCourse c1) { 
    if(!detectConflict(c1)) {
     if(c1.availableTo(this)) {
    CampusCourses.add(c1); 
    c1.setStudentRoster(this); 
    c1.EnrollStudent(); 
    this.addToCampusEnrolledCredits(c1);
    this.addToPersonTuitionFee(c1);               //Increment Tuition Fee after Enrolling Student In Campus Course 
    //System.out.println("Success you have added "+ c1.getName());
    for(int k = 0; k < c1.getSchedule().size(); k++) {  
		Schedule.add(c1.getSchedule().get(k)); 	}
	Collections.sort(Schedule);         
	return;	}}
    if(!c1.availableTo(this)) {
    System.out.println(this.getName() + " can't add Campus Course " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() + " " + c1.getName() + ". Because this Campus course has enough student."); return;}
    if(detectConflict(c1)) { printScheduleConflict(c1);}
    }                     //Only Campus Courses have a Schedule Conflict
	
	public void addCourse(OnlineCourse oc1) {
	if(oc1.availableTo(this)) {	
	OnlineCourses.add(oc1); 
	oc1.setStudentRoster(this);
    this.addToOnlineEnrolledCredits(oc1);	       //update total credits that the Student is enrolled in
	this.addToOnlineTuitionFee(oc1);
	//System.out.println("Success you have added "+ oc1.getName());
	}               //Increment Tuition Fee after enrolling Student in Online course
	else {printOnlineCourseConflict(oc1);}}
	
	
	public void dropCourse(CampusCourse c1) {
		for(int k = 0; k < CampusCourses.size(); k++) {
			int A = CampusCourses.get(k).getCourseNumber();
			int B = c1.getCourseNumber(); 
			if(A == B) {      //Checks if Student is enrolled in the course 
				boolean NoOnline = false; 
				boolean EnoughCredits = false;   
			if(OnlineCourses.isEmpty()) {
					NoOnline = true; 
				}
			 EnoughCredits = ableToDrop(c1); 
		     if(NoOnline) {
		    	 CampusCourses.remove(c1); 
		    	 removeCampusEnrolledCredits(c1);
		    	 c1.DropStudent(); 
		    	 addToPersonTuitionFee(c1);
				 return; }
		     if(EnoughCredits) {
		    	 CampusCourses.remove(c1); 
		    	 removeCampusEnrolledCredits(c1);
		    	 c1.DropStudent(); 
		    	 addToPersonTuitionFee(c1);
				 return; }
		     if(!EnoughCredits) {
		     System.out.println(this.getName() + " can't drop this CampusCourse, because student don't has enough campus course credit to hold online course"); return; }}}
		     System.out.println("The course " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() + " could not be dropped because " + this.getName() + " is not enrolled in " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber());}
	
	public void dropCourse(OnlineCourse oc1) {
		for(int k = 0; k < OnlineCourses.size(); k++) {
			int A = OnlineCourses.get(k).getCourseNumber(); 
			int B = oc1.getCourseNumber(); 
			if(A == B) {      //Checks if Student is enrolled in the course 
				OnlineCourses.remove(oc1);
				removeOnlineEnrolledCredits(oc1); 
				subFromOnlineTuitionFee(oc1); return;}}
		System.out.println("The course " + oc1.getDepartment().getDepartmentName() + oc1.getCourseNumber() + " could not be dropped because " + this.getName() + " is not enrolled in " + oc1.getDepartment().getDepartmentName() + oc1.getCourseNumber()); }
	
	//Print Functions 
	
	public void printOnlineCourseConflict(OnlineCourse oc1) {
		System.out.println("Student " + this.getName() + " has only " + this.getEnrolledCampusCredits() + " on campus credits enrolled. Students should have at least 6 credits registered before registering online courses.");
		System.out.println(this.getName() + " can't add online Course " + oc1.getDepartment().getDepartmentName() + oc1.getCourseNumber() + " " + oc1.getName() + ". Because this student don't have enough Campus course credit.");
	}
	
	public void printScheduleConflict(CampusCourse c1) {
		for(int k = 0; k < CampusCourses.size(); k++) {
	        for(int l = 0; l  < CampusCourses.get(k).getSchedule().size(); l++) {
	   	    for(int m = 0; m < c1.getSchedule().size(); m++) {
	       	int A = CampusCourses.get(k).getSchedule().get(l);
	   	    int B = c1.getSchedule().get(m); 
	   	    	if(A == B) {
	       		int ScheduleNum = c1.getSchedule().get(m); 	
	       		int TimeIndex = (ScheduleNum % 10) - 1; 
				int DayIndex = (ScheduleNum / 100) - 1; 	
				String day = Week[DayIndex]; 
				String time = Time[TimeIndex];
	       		String Department1 = c1.getDepartment().getDepartmentName(); 
	   			int  Num1 = c1.getCourseNumber(); 
	   			System.out.println(Department1 + Num1 + " course cannot be added to " + name + "'s Schedule. " + Department1 + Num1 + " conflicts with " + CampusCourses.get(k).getDepartment().getDepartmentName() + CampusCourses.get(k).getCourseNumber() + ". Conflicting time slot is " + day + " " + time + ".");
	   		 }
	   	    }
	      }
	   } 	
	}

	
	

}