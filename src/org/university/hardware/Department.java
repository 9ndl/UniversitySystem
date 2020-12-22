package org.university.hardware;

import org.university.software.*;
import org.university.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable{
   
	private ArrayList<Student> Students;
	private ArrayList<CampusCourse> CampusCourses; 
	private ArrayList<OnlineCourse> OnlineCourses;
	private ArrayList<Professor> Professors; 
	private ArrayList<Staff> StaffList; 
	private String Name; 
	
	//Constructors 
	public Department() { 
		Name = "unknown"; 
		Students = new ArrayList<Student>();
		Professors = new ArrayList<Professor>();
		CampusCourses = new ArrayList<CampusCourse>(); 
		OnlineCourses = new ArrayList<OnlineCourse>();;
		StaffList = new ArrayList<Staff>();
	}
	
	//Setters and Getters 
	public String getDepartmentName(){
		return Name; }

	public void setDepartmentName(String aName){
		Name = aName;}
	
	public ArrayList<Student> getStudentList() {
		return Students; }
	
	public ArrayList<CampusCourse> getCampusCourseList()  {
		   return CampusCourses; }
	
	public ArrayList<OnlineCourse> getOnlineCourseList()  {
	   return OnlineCourses; }
	
	public ArrayList<Professor> getProfessorList() {
	 return Professors; }
	
	public ArrayList<Staff> getStaffList() {
		 return StaffList; }
 
	
	public void addStudent(Student s1) {
		s1.setDepartment(this); 
	    Students.add(s1);  }
	
	public void addProfessor(Professor p1) {
		Professors.add(p1); 
		p1.setDepartment(this); }
	
	public void addCourse(CampusCourse c1) { 
		c1.setDepartment(this); 
		CampusCourses.add(c1);  }
	
	public void addCourse(OnlineCourse c1) { 
		c1.setDepartment(this); 
		OnlineCourses.add(c1);  }
	
	public void addStaff(Staff sf1) { 
		if (StaffList == null) { 
			StaffList = new ArrayList<Staff>(); }
		sf1.setDepartment(this); 
		StaffList.add(sf1);  }  
	
	//Printing Students and Professors of a certain Department 
	
	public void printStudentList() {
		 for( int j = 0; j < Students.size(); j++) {
    		 System.out.println(Students.get(j).getName()); }}
	
	public void printProfessorList() {
		System.out.println("The professor list for department " + this.getDepartmentName());
		 for( int j = 0; j < Professors.size(); j++) {
    		 System.out.println(Professors.get(j).getName()); } }
	
	public void printCourseList() {
		System.out.println("The course list for department " + this.getDepartmentName());
		 for(int j = 0; j < CampusCourses.size(); j++) {
			 System.out.println(CampusCourses.get(j).getDepartment().getDepartmentName() + CampusCourses.get(j).getCourseNumber() + " " + CampusCourses.get(j).getName());
			 }
		 for(int i = 0; i < OnlineCourses.size(); i++) {
			 System.out.println(OnlineCourses.get(i).getDepartment().getDepartmentName() + OnlineCourses.get(i).getCourseNumber() + " " + OnlineCourses.get(i).getName());
			 }
		 System.out.println("");
		 return;
	}
	
	public void printStaffList() {
		 for( int j = 0; j < StaffList.size(); j++) {
  		 System.out.println(StaffList.get(j).getName()); }}
}
