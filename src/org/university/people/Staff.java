package org.university.people;

import java.io.Serializable;

import org.university.hardware.*; 
import org.university.software.*;

public class Staff extends Employee {
	private Department department;
	private double PayRate; 
	private double Hours;
	private int TuitionFee; 
	
	//Setters and Getters 
	
	public void setMonthlyHours(double hours) {
		Hours = hours;}
	
	public void setPayRate(double Rate) {
		PayRate = Rate;}
	
	public void setDepartment(Department adepartment) {
		department = adepartment;}
	
	public Department getDepartment() {
		return department; 
	}
	
	public double getHours() {
		return Hours;}
	
	public int getTuitionFee() {
		return TuitionFee;}
	
	//Abstract Methods inherited from Employee
	
	public double earns() {
		return (Hours*PayRate);}
	
	public void raise(double percent) {
		PayRate = (PayRate + PayRate*(percent/100)); 
	}
	
	//Tuition Calculations for Online or Campus Course Staff is taking 
	
	public void CalculateOnlineTuitionFee(OnlineCourse oc1) {          
		if(oc1.getCredits() == 3) {   
		TuitionFee = 2000;}
		else if (oc1.getCredits() == 4) {
	    TuitionFee = 3000;}}
	
	public void CalculateCampusTuitionFee(CampusCourse c1) {
		TuitionFee = (c1.getCredits() * 300);}
	
	//Adding Courses to Schedule Methods 
	
	public void addCourse(CampusCourse c1) { 
		if(CampusCourses.isEmpty() && OnlineCourses.isEmpty()) {  //If Staff Member is not signed up for any courses 
		CampusCourses.add(c1); 
		CalculateCampusTuitionFee(c1);
		c1.setStudentRoster(this); 
		}
		else if(!CampusCourses.isEmpty() && OnlineCourses.isEmpty()) { 	//If Staff Member has one Campus Course
		 System.out.println(CampusCourses.get(0).getDepartment().getDepartmentName() + CampusCourses.get(0).getCourseNumber() +" is removed from " + Name + "'s schedule(Staff can only take one class at a time). " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() +" has been added to " + Name + "'s Schedule.");
		 CampusCourses.remove(CampusCourses.get(0));
		 CampusCourses.add(c1);
		 c1.setStudentRoster(this); 
		 CalculateCampusTuitionFee(c1);}
		else if (CampusCourses.isEmpty() && !OnlineCourses.isEmpty()) {  //If Staff Member has one Online course
		 System.out.println(OnlineCourses.get(0).getDepartment().getDepartmentName() + OnlineCourses.get(0).getCourseNumber() +" is removed from " + Name + "'s schedule(Staff can only take one class at a time). " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() +" has been added to " + Name + "'s Schedule.");
		 OnlineCourses.remove(OnlineCourses.get(0));
		 CampusCourses.add(c1);
		 c1.setStudentRoster(this);
		 CalculateCampusTuitionFee(c1);}}
	
	public void addCourse(OnlineCourse c1) {
		if(CampusCourses.isEmpty() && OnlineCourses.isEmpty()) {  //If Staff Member is not signed up for any courses 
		OnlineCourses.add(c1); 
		CalculateOnlineTuitionFee(c1);
		c1.setStudentRoster(this);
		}
		else if(!CampusCourses.isEmpty() && OnlineCourses.isEmpty()) { 	//If Staff Member has one Campus Course
		 System.out.println(CampusCourses.get(0).getDepartment().getDepartmentName() + CampusCourses.get(0).getCourseNumber() +" is removed from " + Name + "'s schedule(Staff can only take one class at a time). " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() +" has been added to " + Name + "'s Schedule.");
		 CampusCourses.remove(CampusCourses.get(0));
		 OnlineCourses.add(c1);
		 CalculateOnlineTuitionFee(c1);
		 c1.setStudentRoster(this);;}
		else if (CampusCourses.isEmpty() && !OnlineCourses.isEmpty()) {  //If Staff Member has one Online course
		 System.out.println(OnlineCourses.get(0).getDepartment().getDepartmentName() + OnlineCourses.get(0).getCourseNumber() +" is removed from " + Name + "'s schedule(Staff can only take one class at a time). " + c1.getDepartment().getDepartmentName() + c1.getCourseNumber() +" has been added to " + Name + "'s Schedule.");
		 OnlineCourses.remove(OnlineCourses.get(0));
		 OnlineCourses.add(c1);
		 CalculateOnlineTuitionFee(c1);
		 c1.setStudentRoster(this);;}}


	

}
