package org.university.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.university.software.*;

public class Classroom implements Serializable{

	private String RoomNum; 
	private ArrayList<CampusCourse> Courses; 
	private ArrayList<Integer> Schedule; 
	private String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri" }; 
	private String[] Time = {"8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm", "2:00pm to 3:15pm", "3:30pm to 4:45pm"}; 
	
	
	public Classroom() {
		Courses = new ArrayList<CampusCourse>();
		Schedule = new ArrayList<Integer>();
	}
	 
	public void setRoomNumber(String roomnum) {
		RoomNum = roomnum; 
	}
	
	public String getRoomNumber() {
		return RoomNum; 
	}
	
	public ArrayList<CampusCourse> getCourses() {
		return Courses; 
	}
	
	public ArrayList<Integer> getSchedule() {
		return Schedule; 
	}
	
	//Adding to Coursed to Room, detecting a conflict, and printing
	public boolean detectConflict(CampusCourse CC) {
	    boolean conflict = false; 
		for(int i = 0; i < Schedule.size(); i++) {
	    	for(int j = 0; j < CC.getSchedule().size(); j++){
	    		int A = Schedule.get(i); 
	    		int B = CC.getSchedule().get(j);
	    		if(A == B){
	    			conflict = true; 
	    			return conflict; 
	    		} 
	    	} 
	    }
		return conflict; 
	}
	
	public void addCourse(CampusCourse CC) {
	    if(!detectConflict(CC)){
	    	Courses.add(CC); 
	        CC.setClassroom(this);
	        for(int i = 0; i < CC.getSchedule().size(); i++){
	    		Schedule.add(CC.getSchedule().get(i));
	    	}
	    	Collections.sort(Schedule); 
	    	return; 
	    }
	    for(int i = 0; i < Courses.size(); i++) {
	        for(int j = 0; j  < Courses.get(i).getSchedule().size(); j++) {
	   	    for(int k = 0; k < CC.getSchedule().size(); k++) {
	       	int A = Courses.get(i).getSchedule().get(j);
	   	    int B = CC.getSchedule().get(k); 
	   	    	if(A == B) {   
	   	    		int ScheduleNum = CC.getSchedule().get(k); 
	   	    		int TimeIndex = (ScheduleNum % 10) - 1; 
					int DayIndex = (ScheduleNum / 100) - 1; 	
					String day = Week[DayIndex]; 
				    String time = Time[TimeIndex];
	       		 	String DepName = CC.getDepartment().getDepartmentName(); 
	       		 	int  Num1 = CC.getCourseNumber(); 
	   			 System.out.println(DepName + Num1 + " conflicts with " + Courses.get(i).getDepartment().getDepartmentName() + Courses.get(i).getCourseNumber() + ". Conflicting time slot " + day + " " + time + ". " + DepName + Num1 + " course cannot be added to " + RoomNum + "'s Schedule."); 
	   		   }
	   	    }
	      }
	   } 
	}
	
	public void printSchedule() {
		System.out.println("The schedule for classroom " + this.getRoomNumber());
		for(int i = 0; i < Schedule.size(); i++) {   
			for(int j = 0; j < Courses.size(); j++) {
				for(int k = 0; k < Courses.get(j).getSchedule().size(); k++) {
					int ScheduleNum = Courses.get(j).getSchedule().get(k); 
					if(Schedule.get(i) == ScheduleNum) {
						int TimeIndex = (ScheduleNum % 10) - 1; 
						int DayIndex = (ScheduleNum / 100) - 1; 
						String day = Week[DayIndex]; 
						String time = Time[TimeIndex];
						String DepName = Courses.get(j).getDepartment().getDepartmentName(); 
						String CourseName = Courses.get(j).getName(); 
						int CourseNumber = Courses.get(j).getCourseNumber();
						System.out.println("" + day + " " + time + " " + DepName + CourseNumber + " " + CourseName);
			   } 
		    }
		  }
		}
      }	
	
}
