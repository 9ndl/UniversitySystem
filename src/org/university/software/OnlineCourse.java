package org.university.software;
 
import java.io.Serializable;

import org.university.people.*;

public class OnlineCourse extends Course implements Serializable {

	private int Number; 
	private int OnlineCredits = 0;
	private String Name; 
	
	//Setters and Getters 
	public String getName(){
		return Name;}

	public void setName(String aName){
		Name = aName;}
	 
	public int getCourseNumber() { 
		return Number; }
	
	public void setCourseNumber(int aNumber) {
		Number = aNumber;}
	
	public int getCredits(){
		return OnlineCredits;}

	public void setCreditUnits(int numCredits){
		OnlineCredits = numCredits; }
	
    public boolean availableTo(Student s1) {//Check if student has more than 6 Campus credits for online courses 
    	boolean Available = false; 
    	if(s1.getEnrolledCampusCredits() >= 6) {
    		Available = true; 
    		return Available; }
    	return Available;  }  

}
