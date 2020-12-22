package org.university.software;

//import java.io.Serializable;
import java.util.ArrayList;
import org.university.hardware.*; 
import org.university.people.*;


public class CampusCourse extends Course {
     private int MaxStudents = 0; 
     private int StudentsEnrolled = 0; 
 	 private int CampusCredits = 0;
     private Classroom  Room; 
     private String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri" }; 
 	 private String[] Time = {"8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm", "2:00pm to 3:15pm", "3:30pm to 4:45pm"};
 	 
    public void setMaxStudents(int Max){
   	  MaxStudents = Max; }

   	public int getMaxStudents(){
   		return MaxStudents; }
   	
   	public void setClassroom(Classroom room) {
   		Room = room; 
   	}
   	
  	public Classroom getClassroom(){
  		return Room; }

    public int getStudentsEnrolled(){
    	return StudentsEnrolled;  }
    
	public int getCredits(){
		return CampusCredits;}

	public void setCreditUnits(int numCredits){
		CampusCredits = numCredits; }
	
	public void setMaxCourseLimit(int max) {
		MaxStudents = max; }
	
	public void setRoomAssigned(Classroom aRoom) {
		if (!detectClassConflict(aRoom)) {
		aRoom.addCourse(this);
		Room = aRoom;     
		return;	} 
		else {printScheduleConflict(aRoom);}}
	
    //Other methods 
   	public void EnrollStudent(){
    	StudentsEnrolled = (StudentsEnrolled + 1); }
   	
   	public void DropStudent() {
   		StudentsEnrolled = StudentsEnrolled - 1; }
    
   	public ArrayList<Integer> getSchedule() {
   		return Schedule;  }
    
    
    //Detection Methods 
    
    public boolean availableTo(Student s1) {
    	boolean Available = true; 
    	if(StudentsEnrolled == MaxStudents) {
    		Available = false; 
    		return Available; }
    	return Available;  }

    //Print Method 
    
    public void printSchedule() {
    	for (int i = 0; i < Schedule.size(); i++) {
    		int ScheduleNum = Schedule.get(i);
    		int TimeIndex = (ScheduleNum % 10) - 1; 
			int DayIndex = (ScheduleNum / 100) - 1; 
			String time = Time[TimeIndex];
			String day = Week[DayIndex]; 
			System.out.println(day + " " + time + " " + Room.getRoomNumber());} } 
    
	public void printScheduleConflict(Classroom cr1) {
		for(int k = 0; k < cr1.getCourses().size(); k++) {
	        for(int l = 0; l  < cr1.getCourses().get(k).getSchedule().size(); l++) {
	   	    for(int m = 0; m < Schedule.size(); m++) {
	       	int A = cr1.getCourses().get(k).getSchedule().get(l);
	   	    int B = Schedule.get(m); 
	   	    	if(A == B) {
	   	    		int ScheduleNum = Schedule.get(m); 
	   	    		int TimeIndex = (ScheduleNum % 10) - 1; 
					int DayIndex = (ScheduleNum / 100) - 1; 
					String day = Week[DayIndex]; 
				    String time = Time[TimeIndex];
	       		 	String Department1 = DepartmentName.getDepartmentName(); ; 
	       		 	System.out.println( Department1 + number + " conflicts with " + cr1.getCourses().get(k).getDepartment().getDepartmentName() + cr1.getCourses().get(k).getCourseNumber() + ". Conflicting time slot is " + day + " " + time + "." +  Department1 + number + " course cannot be added to " + cr1.getRoomNumber() + "'s Schedule. ");
	   		 }
	   	    }
	      }
	   }
	 }
	}
