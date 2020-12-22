package org.university.software;

import org.university.hardware.*;
import org.university.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Course implements Serializable{

	protected String Name; 
	protected int number; 
	protected int credits; 
	protected Department DepartmentName; 
	protected ArrayList<Person> StudentRoster; 
	public ArrayList<Integer> Schedule; 
	private Professor TheProfessor = null;
	private String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri" }; 
	private String[] Time = {"8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm", "2:00pm to 3:15pm", "3:30pm to 4:45pm"}; 
	
	

	public Course() {
		Name = "unknown";
		StudentRoster = new ArrayList<Person>();
		Schedule = new ArrayList<Integer>();
	}
	//Setters and Getters 
	public String getName(){
		return Name;}

	public void setName(String aName){
		Name = aName; }
	 
	public int getCourseNumber() { 
		return number; }
	
	public void setCourseNumber(int aNumber) {
		number = aNumber; }
	
	public Department getDepartment(){
		return DepartmentName;}

	public void setDepartment(Department aDep){
		DepartmentName = aDep;}
	
	public void setStudentRoster(Person newStudent) { 
		 StudentRoster.add(newStudent);	}
	
	public void setProfessor(Professor p1) {
		 TheProfessor = p1;	}
	
	public Professor getProfessor() {
		return TheProfessor; }
		
	public ArrayList<Integer> getSchedule() {
		return Schedule;}
	
	public ArrayList<Person> getStudentRoster() { 
		return StudentRoster; }
	
	public void setSchedule(int aSchedule) {
		Schedule.add(aSchedule); }

	public int getCredits() { 
		return credits; }
	
	public void setCredits(int Credits) {
		credits = Credits; }

	//Detect Conflict 
	public boolean detectClassConflict(Classroom aRoom) {
	    boolean Conflict = false; 
		 if(Schedule.size() != 0) {
				 for(int i = 0; i < Schedule.size(); i++) {	

				   for(int j = 0; j < aRoom.getSchedule().size(); j++) {
				  int A = Schedule.get(i); 
				  int B = aRoom.getSchedule().get(j); 
				  if(A == B) {		
			      Conflict = true; 
			      return Conflict;  } } } }
		 		  return Conflict; }
	
	public void printStudentRoster() {
		for(int i = 0; i < StudentRoster.size();i++) {
			System.out.println(StudentRoster.get(i).getName());
		}
	}
	//Abstract Methods 
	public abstract boolean availableTo(Student s1); 
	
	
	
}