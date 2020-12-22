package org.university.people;

import java.io.Serializable;
import java.util.ArrayList;


import org.university.software.*;

public abstract class Person implements Serializable {

	protected String Name; 
	public ArrayList<Integer> Schedule;
	public ArrayList<Staff> StaffList; 
	public ArrayList<CampusCourse> CampusCourses; 
	public ArrayList<OnlineCourse> OnlineCourses; 
	protected String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri" }; 
	protected String[] Time = {"8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm", "2:00pm to 3:15pm", "3:30pm to 4:45pm"};
	
	public Person() {
		Schedule = new ArrayList<Integer>();
		StaffList = new ArrayList<Staff>(); 
		CampusCourses = new ArrayList<CampusCourse>(); 
		OnlineCourses = new ArrayList<OnlineCourse>(); 
	
	}
	
	//Getters and Setters 
	public void setName(String name) {
		Name = name; 	}
	
	public String getName() {
		return Name;	}
	
	public ArrayList<Integer> getSchedule() {
		return Schedule; 
	}
	
	public ArrayList<CampusCourse> getCampusCourses() {
		return CampusCourses; 
	}
	//Abstract Methods 
	public abstract void addCourse(CampusCourse CC); 
	
	public abstract void addCourse(OnlineCourse OC); 
	
	//Detect Conflict and Print Schedule 
	public boolean detectConflict(Course aCourse) {
	    boolean Conflict = false; 
		 if(Schedule.size() != 0) {
				 for(int i = 0; i < Schedule.size(); i++) {	
				   for(int j = 0; j < aCourse.getSchedule().size(); j++) {
				  int A = Schedule.get(i); 
				  int B = aCourse.getSchedule().get(j); 
				  if(A == B) {		
			      Conflict = true;
			      //System.out.println(aCourse.getDepartment().getDepartmentName() + aCourse.getCourseNumber() +
			    		 // " course cannot be added to " + Name + "'s Schedule." );
			      return Conflict;  
			      } 
				  } 
				  } 
				 }
		 		  return Conflict; }
	

	public void printSchedule() {
	for(int i = 0; i < Schedule.size(); i++) {
	    for(int j = 0; j < CampusCourses.size(); j++) {
		   for(int k = 0; k < CampusCourses.get(j).getSchedule().size(); k++) {
			   int ScheduleNum = CampusCourses.get(j).getSchedule().get(k); 
			   int A = Schedule.get(i); 
			   if(A == ScheduleNum) {
				   int TimeIndex = (ScheduleNum % 10) - 1; 
				   int DayIndex = (ScheduleNum / 100) - 1; 
				   String day = Week[DayIndex]; 
			       String time = Time[TimeIndex];
			       String DepartmentName = CampusCourses.get(j).getDepartment().getDepartmentName(); 
				   String CourseName = CampusCourses.get(j).getName(); 
				   int CourseNumber = CampusCourses.get(j).getCourseNumber();
		System.out.println("" + day + " " + time + " " + DepartmentName + CourseNumber + " " + CourseName); }}}}
	    for(int k = 0; k < OnlineCourses.size(); k++) {
	    System.out.println(OnlineCourses.get(k).getCourseNumber() + " " + OnlineCourses.get(k).getName()); }}
	
}
