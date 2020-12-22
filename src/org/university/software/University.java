package org.university.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.university.hardware.*; 

import java.util.ArrayList;

public class University implements Serializable {

	public ArrayList<Department> departmentList = new ArrayList<Department>();
     public ArrayList<Classroom> classroomList = new ArrayList<Classroom>();    
     
     public void printDepartmentList() {
    	 for(int i = 0; i < departmentList.size(); i++) {
    		 System.out.println("" + departmentList.get(i).getDepartmentName()); } }
	
     public void printStudentList(){
    	 for(int i = 0; i < departmentList.size(); i++) {
    		 for( int j = 0; j < departmentList.get(i).getStudentList().size(); j++) {
    		 System.out.println("" + departmentList.get(i).getStudentList().get(j).getName()); } } } 
    	 
     public void printCourseList(){
    	 for(int i = 0; i < departmentList.size(); i++) {
    		 for( int j = 0; j < departmentList.get(i).getCampusCourseList().size(); j++) {
    		 System.out.println(departmentList.get(i).getDepartmentName() + departmentList.get(i).getCampusCourseList().get(j).getCourseNumber() + " " + departmentList.get(i).getCampusCourseList().get(j).getName());}} 
    	 
    	 if(!departmentList.get(0).getOnlineCourseList().isEmpty()) {
    		 for(int k = 0; k < departmentList.size(); k++) {
    			 for( int l = 0; l < departmentList.get(k).getOnlineCourseList().size(); l++) {
    				 System.out.println(departmentList.get(k).getDepartmentName() + departmentList.get(k).getOnlineCourseList().get(l).getCourseNumber() + " " + departmentList.get(k).getOnlineCourseList().get(l).getName()); 
    			 }
    		 }
    	 }
    	 return;
     }
     
     public void printClassroomList() {
    	 for(int i = 0; i < classroomList.size(); i++) {
    		 System.out.println(classroomList.get(i).getRoomNumber());
    	} 
     }
     
     public void printProfessorList() { 
    	 for(int i = 0; i < departmentList.size(); i++) {
    		 for( int j = 0; j < departmentList.get(i).getProfessorList().size(); j++) {
    		 System.out.println("" + departmentList.get(i).getProfessorList().get(j).getName()); } } }
     
     public void printStaffList() { 
    	 System.out.println("Carol"); }
     


public static void saveData(University uni){
	
	
	FileOutputStream fileOut = null;
	ObjectOutputStream objOut= null;

	try 
	{
		fileOut = new FileOutputStream("./root/University.ser");
		objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(uni);
		objOut.close();
		fileOut.close();
     }	
	
	catch(IOException i)
    {
		i.printStackTrace();
    }		
	}

public static University loadData(){	
	FileInputStream fileIn = null;
	ObjectInputStream objIn = null;
	University uni = null;
		
	try
	{
		fileIn = new FileInputStream("./root/University.ser");
		objIn = new ObjectInputStream(fileIn);
		uni = (University) objIn.readObject();
		objIn.close();
		fileIn.close();
	}
	catch(IOException i)
	{
		i.printStackTrace();
	} 
	catch (ClassNotFoundException e) 
	{
		e.printStackTrace();
	}  
	return uni;
	}
	public void printAll() {
		//print departments list
		System.out.println("List of departments:");
		this.printDepartmentList();
		System.out.println("");
		
		//print classroom list
		System.out.println("Classroom list:");
		this.printClassroomList();
		System.out.println("");
		
		//print professorlist
		for(int i = 0; i < departmentList.size();i++) {
				departmentList.get(i).printProfessorList();	
				System.out.println("");
		}
		
		
		//print the course list of departments
		for(int i = 0; i < departmentList.size();i++) {
			departmentList.get(i).printCourseList();
		}
		
		//print classroom schedual.
		for(int i = 0; i < classroomList.size(); i++) {
			classroomList.get(i).printSchedule();
			System.out.println("");
		}
		//print department info
		for(int i = 0; i < departmentList.size();i++) {
			System.out.println("Department "+ departmentList.get(i).getDepartmentName()+"\n");
			System.out.println("Printing Professor Schedules: \n");
			//print professors schedule
			for(int j = 0; j < departmentList.get(i).getProfessorList().size(); ++j) {
				System.out.println("The Schedule for Prof. "+ departmentList.get(i).getProfessorList().get(j).getName());
				departmentList.get(i).getProfessorList().get(j).printSchedule();
				System.out.println("");
			}
			System.out.println("Printing Student Schedules: \n");
			for(int j = 0; j < departmentList.get(i).getStudentList().size(); ++j) {
				System.out.println("The Schedule for Student "+ departmentList.get(i).getStudentList().get(j).getName() +":");
				departmentList.get(i).getStudentList().get(j).printSchedule();
				System.out.println("");
			}
			
			System.out.println("Printing Staff Schedules: \n");
			for(int j = 0; j < departmentList.get(i).getStaffList().size(); ++j) {
				System.out.println("The Schedule for Employee "+ departmentList.get(i).getStaffList().get(j).getName() +":");
				departmentList.get(i).getStaffList().get(j).printSchedule();
				System.out.println("");
			}
			
			System.out.println("The rosters for courses offered by " +departmentList.get(i).getDepartmentName()+"\n" );
			for(int j = 0; j < departmentList.get(i).getCampusCourseList().size(); ++j) {
				System.out.println("The roster for course "+ departmentList.get(i).getCampusCourseList().get(j).getName() +":");
				departmentList.get(i).getCampusCourseList().get(j).printStudentRoster();
				System.out.println("");
			}
		}	
//		// printing student schedules
//		for(int i = 0; i < departmentList.size();i++) {
//			System.out.println("Printing Student Schedules: \n");
//			for(int j = 0; j < departmentList.get(i).getStudentList().size(); ++j) {
//				System.out.println("The Schedule for Student "+ departmentList.get(i).getStudentList().get(j).getName() +":");
//				departmentList.get(i).getStudentList().get(j).printSchedule();
//				System.out.println("");
//				
//			}
//		}
//		
//		//print staff schedule
//		for(int i = 0; i < departmentList.size();i++) {
//			System.out.println("Printing Staff Schedules: \n");
//			for(int j = 0; j < departmentList.get(i).getStaffList().size(); ++j) {
//				System.out.println("The Schedule for Employee "+ departmentList.get(i).getStaffList().get(j).getName() +":");
//				departmentList.get(i).getStaffList().get(j).printSchedule();
//				System.out.println("");
//				
//			}
//		}
		//
	}

}

