package org.university.people;

import org.university.hardware.*; 
import org.university.software.*;

import java.io.Serializable;
import java.util.Collections; 

public class Professor extends Employee {
	
	private double salary; 
	private Department ProfessorDepartment; 
    
	//Setters and Getters 
	public void setSalary(double aSalary) { 
		salary = aSalary;}
	
	public void setDepartment(Department Adepartment) {
		ProfessorDepartment = Adepartment; }
	
	
	
	// Abstract Methods 
	public double earns() {
		return (salary/26);}

	public void raise(double percent) {
		salary = ((percent/100)*salary) + salary;}
    
	//Adding Campus and Online Courses 
	
	public void addCourse(CampusCourse c1) { 
		  
	    if(!detectConflict(c1)) {
	      if(c1.getProfessor() == null) { 
	    CampusCourses.add(c1); 
	    c1.setProfessor(this); 

	    for(int k = 0; k < c1.getSchedule().size(); k++) {  
			Schedule.add(c1.getSchedule().get(k)); 	}
		Collections.sort(Schedule);         
		return;	} 
	      else {System.out.println("The professor " + Name + " cannot be assigned to this course, because professor " + c1.getProfessor().getName() + " is already assinged to the course " + c1.getName());} 
	      return; }
	    else { printScheduleConflict(c1);}}
	
	public void addCourse(OnlineCourse c1) {
		 if(c1.getProfessor() == null) { 
		 OnlineCourses.add(c1);
		 c1.setProfessor(this); 
		 }
		 else {System.out.println("The professor cannot be assigned to this course, because professor " + c1.getProfessor().getName() + " is already assinged to the course " + c1.getName());}}
	

	//Print Methods 
	
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
	   			 System.out.println(Department1 + Num1 + " course cannot be added to " + Name + "'s Schedule. " + Department1 + Num1 + " conflicts with " + CampusCourses.get(k).getDepartment().getDepartmentName() + CampusCourses.get(k).getCourseNumber() + ". Conflicting time slot is " + day + " " + time + ".");
	   		 }
	   	    }
	      }
	   } 	
	}	
   }
