package org.university.software;
import 	org.university.hardware.*;
import	org.university.people.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayOutputStream;


//import org.hrs.system.Employee;
//import org.hrs.system.HRGUI.MenuListener;

public class UniversityGUI extends JFrame{

	private University  uni = new University();
	private JMenuBar menuBar;		//the horizontal container
	private JMenu adminMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	private JMenu fileMenu;
	private JMenu studentmenu;
	

	
	private JMenuItem FileSave;
	private JMenuItem FileLoad;
	private JMenuItem Exit;
	private JMenuItem AddCourse;
	private JMenuItem DropCourse;
	private JMenuItem PrintSchedule;
	private JMenuItem PrintAllInfo;
	
	
	public UniversityGUI(String windowTitle, University univ1) {
		super(windowTitle);

		uni = univ1;


		setSize(300, 100); //size of the window
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Welcome to the University." +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
	}
	
	
	public void buildGUI() 
	{
		menuBar = new JMenuBar();
     	
		// Employee Student Menu
		// three menus in the bar
		adminMenu = new JMenu("Administrators");
		// creating the File option in the menu bar
		fileMenu = new JMenu("File");
		studentmenu = new JMenu("Students");
		
		// assign the naming for the Save and Load menu
		FileSave = new JMenuItem("Save");
		FileLoad = new JMenuItem("Load");
		Exit = new JMenuItem("Exit");
		AddCourse = new JMenuItem("Add Course");
		DropCourse = new JMenuItem("Drop Course");
		PrintSchedule = new JMenuItem("Print Schedule");
		PrintAllInfo = new JMenuItem("Print All Info");
				//JMenuItem's, via AbstractButton, have a method addActionListener(ActionListener)
				//The innerclass is implementing ActionListener, so it can take action when the JMenuItem is clicked.
		//File menu Items
		FileSave.addActionListener(new MenuListener());
	    FileLoad.addActionListener(new MenuListener());
		Exit.addActionListener(new MenuListener());
	    fileMenu.add(FileSave);
		fileMenu.add(FileLoad);
		fileMenu.add(Exit);
		//Students menu Items
		AddCourse.addActionListener(new MenuListener());
		DropCourse.addActionListener(new MenuListener());
		PrintSchedule.addActionListener(new MenuListener());
		studentmenu.add(AddCourse);
		studentmenu.add(DropCourse);
		studentmenu.add(PrintSchedule);
		//Administrators Menu items
		PrintAllInfo.addActionListener(new MenuListener());
		adminMenu.add(PrintAllInfo);
		
		
		
		menuBar.add(fileMenu);
		menuBar.add(studentmenu);
	    menuBar.add(adminMenu);
	   
		setJMenuBar(menuBar);
	}
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(Exit))
			{
				System.exit(0);	
			}
			else if(source.equals(FileSave)) {
				handleFileSave();
			}
			else if(source.equals(FileLoad)) {
				handleFileLoad();
			}
			else if(source.equals(PrintAllInfo)) {
				handlePrintAllInfo();
			}
			else if(source.equals(AddCourse)) {
				handleAddCourse();
			}
			else if(source.equals(DropCourse)) {
				handleDropCourse();
			}
			else if(source.equals(PrintSchedule)) {
				handlePrintSchedule();
			}
		}
		

		private void handleFileSave() {
			University.saveData(uni);
		}
		private void handleFileLoad() {
			uni = University.loadData();
		}
		private void handlePrintAllInfo() {
			
			final class SimpleWindow extends JFrame{
				
//				private class PrintStream{
//					
//				}
//				PrintStream ps = new PrintStream(uni.printAll());
				private SimpleWindow(){
					super("A Simple Window");
					setLayout(new GridLayout());
					final int WINDOW_WIDTH = 500;
					final int WINDOW_HEIGHT = 500;
					setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(baos);
					// IMPORTANT: Save the old System.out!
					PrintStream old = System.out;
					// Tell Java to use your special stream
					System.setOut(ps);
					// Print some output: goes to your special stream
					uni.printAll();
					// Put things back
					System.out.flush();
					System.setOut(old);
					// Show what happened
					String data = baos.toString();
					//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JTextArea sampleTextArea = new JTextArea ();
					sampleTextArea.append(data);
					JScrollPane sampleScrollPane = new JScrollPane (sampleTextArea,
													JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
													JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					this.add(sampleScrollPane);
					setVisible(true);
				}
			}	 
			 SimpleWindow myWindow = new SimpleWindow();
		}
		
		private void handleAddCourse() {

			JPanel pane = new JPanel();
	        pane.setLayout(new GridLayout(0, 2, 2, 2));

	        JTextField Snamefield = new JTextField(5);
	        JTextField DepartmentField = new JTextField(5);
	        JTextField coursenumfield = new JTextField(5);

	        pane.add(new JLabel("Student Name:"));
	        pane.add(Snamefield);

	        pane.add(new JLabel("Department:"));
	        pane.add(DepartmentField);
	        
	        pane.add(new JLabel("Course #:"));
	        pane.add(coursenumfield);
	        int option = JOptionPane.showConfirmDialog(null, pane, "Add Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	        if (option == JOptionPane.OK_OPTION) {
	        	String data;
	            String Sname = Snamefield.getText();
	            String Sdepartment = DepartmentField.getText();
	            System.out.println("Name: "+Sname);
	            System.out.println("Department: "+Sdepartment);
	            int Coursenum = Integer.parseInt(coursenumfield.getText());
	            System.out.println("Course#: " + coursenumfield.getText());
	            int dfoundflag = 0;
	            int namefoundflag = 0;
	            int coursenumfoundflag = 0;
	            //add campus course 
	            int flag3 = 0;
	            for(int i = 0; i < uni.departmentList.size(); i++) {
	            	String C = uni.departmentList.get(i).getDepartmentName();
	            	if(C.equals(Sdepartment)) {
	            		dfoundflag = 1;
	            	for(int j = 0; j < uni.departmentList.get(i).getStudentList().size(); j++) {	
	            		String A = uni.departmentList.get(i).getStudentList().get(j).getName();
	            		if(A.equals(Sname)) {
	            			namefoundflag = 1;
	            		for(int k = 0; k < uni.departmentList.get(i).getCampusCourseList().size(); k++) {
	            			int B = uni.departmentList.get(i).getCampusCourseList().get(k).getCourseNumber();
	            			
	            			
	            			String D = uni.departmentList.get(i).getCampusCourseList().get(k).getName();
	            			System.out.println("A "+A);
	            			System.out.println("B " +B);
	            			boolean a = uni.departmentList.get(i).getCampusCourseList().get(k).availableTo(uni.departmentList.get(i).getStudentList().get(j));
	            			if((B == Coursenum)) {
	            				coursenumfoundflag = 1;
	            				//class and student are found 
	            				if(a) {
	            					
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					//uni.printAll();
	            					uni.departmentList.get(i).getStudentList().get(j).addCourse(uni.departmentList.get(i).getCampusCourseList().get(k));

	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					data = baos.toString();
		            				System.out.println("inside");
		            	            pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		            	            if(data.equals("")) {
		            	            	data="Success you have added "+ D;
		            	            }
		            	            
		            	            pane.add(new JLabel(data));
		            	            JOptionPane.showMessageDialog(null, pane,"Message",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}else {
	            					pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		            	            pane.add(new JLabel(A + " can't add Campus Course " + C+B+" " +D+" . Because this Campus course has enough student"));
		            	            JOptionPane.showMessageDialog(null, pane,"Error",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}
	            			}
	            		}
	            	}
	            		if(coursenumfoundflag == 1) {
        	            	break;
        	            }	
	            	}
	            } 
	            	if(coursenumfoundflag == 1) {
    	            	break;
    	            }
	            }

	            // add online course
	            int flag1=1;
	            for(int i = 0; i < uni.departmentList.size(); i++) {
	            	for(int j = 0; j < uni.departmentList.get(i).getStudentList().size(); j++) {	
	            		for(int k = 0; k < uni.departmentList.get(i).getOnlineCourseList().size(); k++) {
	            			String A = uni.departmentList.get(i).getStudentList().get(j).getName();
	            			int B = uni.departmentList.get(i).getOnlineCourseList().get(k).getCourseNumber();
	            			String C = uni.departmentList.get(i).getDepartmentName();
	            			String D = uni.departmentList.get(i).getOnlineCourseList().get(k).getName();
	            			System.out.println("A "+A);
	            			System.out.println("B " +B);
	            			
	            			if((A.equals(Sname))&&(B == Coursenum)&&(C.equals(Sdepartment))) {
	            				coursenumfoundflag = 1;
	            				//class and student are found 
	            				if(uni.departmentList.get(i).getOnlineCourseList().get(k).availableTo(uni.departmentList.get(i).getStudentList().get(j))) {
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					//uni.printAll();
	            					uni.departmentList.get(i).getStudentList().get(j).addCourse(uni.departmentList.get(i).getOnlineCourseList().get(k));

	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					data = baos.toString();
		            				System.out.println("inside");
		            	            pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		            	            if(data.equals("")) {
		            	            	data="Success you have added "+ D;
		            	            }
		            	            pane.add(new JLabel(data));
		            	            JOptionPane.showMessageDialog(null, pane, "Success",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}else {
	            					pane = new JPanel();
	            					uni.departmentList.get(i).getStudentList().get(j).addCourse(uni.departmentList.get(i).getOnlineCourseList().get(k));
		            	           // pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		            	            pane.setLayout(new GridLayout());
		            	            int credit = uni.departmentList.get(i).getStudentList().get(j).getEnrolledCampusCredits();
		            	            String coursename = Sdepartment + Coursenum +" "+ uni.departmentList.get(i).getOnlineCourseList().get(k).getName();
		            	            pane.add(new JLabel("Student "+ Sname +" has only "+ credit + " on campus enrolled. Sjould have at least 6 credits registered before registering online courses."
		            	            		+" "+ Sname+" can't add online course "+coursename+". Because this student doesnt have enouoh campus"));
		            	            JOptionPane.showMessageDialog(null, pane, "Error",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}
	            				
	            			}
	           
	            		}
	            		if(coursenumfoundflag == 1) {
	            			break;
	            		}
	            	}
	            	if(coursenumfoundflag == 1) {
            			break;
            		}
	            }
		         if(dfoundflag == 0) {
						pane = new JPanel();
	    	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	    	            pane.add(new JLabel("Department "+ Sdepartment+ " doesnt exist"));
	    	            JOptionPane.showMessageDialog(null, pane);
		         }else if(namefoundflag == 0) {
		        	 pane = new JPanel();
	 	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	 	            pane.add(new JLabel("Student "+ Sname+ " doesnt exist"));
	 	            JOptionPane.showMessageDialog(null, pane);
		         }
	            else if(coursenumfoundflag == 0) {
	            		pane = new JPanel();
		 	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		 	            pane.add(new JLabel("Course number: "+ Coursenum+ " doesnt exist"));
		 	            JOptionPane.showMessageDialog(null, pane);
	            }
	        }
			
			
		}
		private void handleDropCourse() {
			JPanel pane = new JPanel();
	        pane.setLayout(new GridLayout(0, 2, 2, 2));

	        JTextField Snamefield = new JTextField(5);
	        JTextField DepartmentField = new JTextField(5);
	        JTextField coursenumfield = new JTextField(5);

	        pane.add(new JLabel("Student Name:"));
	        pane.add(Snamefield);

	        pane.add(new JLabel("Department:"));
	        pane.add(DepartmentField);
	        
	        pane.add(new JLabel("Course #:"));
	        pane.add(coursenumfield);
	        int option = JOptionPane.showConfirmDialog(null, pane, "Drop Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	        if (option == JOptionPane.OK_OPTION) {
	        	String data;
	            String Sname = Snamefield.getText();
	            String Sdepartment = DepartmentField.getText();
	            System.out.println("Name: "+Sname);
	            System.out.println("Department: "+Sdepartment);
	            int Coursenum = Integer.parseInt(coursenumfield.getText());
	            System.out.println("Course#: " + coursenumfield.getText());
	            int dfoundflag = 0;
	            int namefoundflag = 0;
	            int coursenumfoundflag = 0;

	            
	            for(int i = 0; i < uni.departmentList.size(); i++) {
	            	String C = uni.departmentList.get(i).getDepartmentName();
	            	if(C.equals(Sdepartment)) {
	            		dfoundflag = 1;
	            	for(int j = 0; j < uni.departmentList.get(i).getStudentList().size(); j++) {	
	            		String A = uni.departmentList.get(i).getStudentList().get(j).getName();
	            		if(A.equals(Sname)) {
	            			namefoundflag = 1;
	            		for(int k = 0; k < uni.departmentList.get(i).getCampusCourseList().size(); k++) {
	            			int B = uni.departmentList.get(i).getCampusCourseList().get(k).getCourseNumber();
	            			
	            			
	            			String D = uni.departmentList.get(i).getCampusCourseList().get(k).getName();
	            			System.out.println("A "+A);
	            			System.out.println("B " +B);
	            			if((B == Coursenum)) {
	            				coursenumfoundflag = 1;
	            				//class and student are found 
	            				if(uni.departmentList.get(i).getStudentList().get(j).ableToDrop(uni.departmentList.get(i).getCampusCourseList().get(k))) {
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					//uni.printAll();
	            					uni.departmentList.get(i).getStudentList().get(j).dropCourse(uni.departmentList.get(i).getCampusCourseList().get(k));

	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					data = baos.toString();
		            				System.out.println("inside");
		            	            pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		            	            pane.add(new JLabel("Successfully droped course"));
		            	            JOptionPane.showMessageDialog(null, pane,"Success",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}else {
	            					pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					//uni.printAll();
	            					uni.departmentList.get(i).getStudentList().get(j).dropCourse(uni.departmentList.get(i).getCampusCourseList().get(k));

	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					data = baos.toString();
		            	            pane.add(new JLabel(data));
		            	            JOptionPane.showMessageDialog(null, pane,"Error",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}
	            			}
	            		}
	            	}
	            	if(coursenumfoundflag == 1) {
	            		break;
	            	}
	            	}
	            } 
	            	if(coursenumfoundflag == 1) {
	            		break;
	            	}
	            }

	            
	            for(int i = 0; i < uni.departmentList.size(); i++) {
	            	for(int j = 0; j < uni.departmentList.get(i).getStudentList().size(); j++) {	
	            		for(int k = 0; k < uni.departmentList.get(i).getOnlineCourseList().size(); k++) {
	            			String A = uni.departmentList.get(i).getStudentList().get(j).getName();
	            			int B = uni.departmentList.get(i).getOnlineCourseList().get(k).getCourseNumber();
	            			String C = uni.departmentList.get(i).getDepartmentName();
	            			String D = uni.departmentList.get(i).getOnlineCourseList().get(k).getName();
	            			System.out.println("A "+A);
	            			System.out.println("B " +B);
	            			
	            			if((A.equals(Sname))&&(B == Coursenum)&&(C.equals(Sdepartment))) {
	            				coursenumfoundflag = 1;
	            				dfoundflag = 1;
	            				coursenumfoundflag = 1;
	            				//class and student are found 
	            				if(dfoundflag == 1) {
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					//uni.printAll();
	            					uni.departmentList.get(i).getStudentList().get(j).dropCourse(uni.departmentList.get(i).getOnlineCourseList().get(k));

	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					data = baos.toString();
		            				System.out.println("inside");
		            	            pane = new JPanel();
		            	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		            	            pane.add(new JLabel("Seccusfully dropped course"));
		            	            JOptionPane.showMessageDialog(null, pane, "Success",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}else {
	            					pane = new JPanel();
	            					uni.departmentList.get(i).getStudentList().get(j).dropCourse(uni.departmentList.get(i).getOnlineCourseList().get(k));
		            	           // pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		            	            pane.setLayout(new GridLayout());
		            	            int credit = uni.departmentList.get(i).getStudentList().get(j).getEnrolledCampusCredits();
		            	            String coursename = Sdepartment + Coursenum +" "+ uni.departmentList.get(i).getOnlineCourseList().get(k).getName();
		            	            pane.add(new JLabel("Student "+ Sname +" has only "+ credit + " on campus enrolled. Sjould have at least 6 credits registered before registering online courses."
		            	            		+" "+ Sname+" can't add online course "+coursename+". Because this student doesnt have enouoh campus"));
		            	            JOptionPane.showMessageDialog(null, pane, "Error",JOptionPane.PLAIN_MESSAGE);
		            	            break;
	            				}
	            				
	            			}
	            			
	            		}
	            		if(coursenumfoundflag == 1) {
	            			break;
	            		}
		            		
	            	}
	            	if(coursenumfoundflag == 1) {
            			break;
            		}
	            }
		         if(dfoundflag == 0) {
						pane = new JPanel();
	    	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	    	            pane.add(new JLabel("Department "+ Sdepartment+ " doesnt exist"));
	    	            JOptionPane.showMessageDialog(null, pane);
		         }else if(namefoundflag == 0) {
		        	 pane = new JPanel();
	 	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	 	            pane.add(new JLabel("Student "+ Sname+ " doesnt exist"));
	 	            JOptionPane.showMessageDialog(null, pane);
		         }
	            else if(coursenumfoundflag == 0) {
	            		pane = new JPanel();
		 	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		 	            pane.add(new JLabel("Course number: "+ Coursenum+ " doesnt exist"));
		 	            JOptionPane.showMessageDialog(null, pane);
	            }
	        }
		}
		private void handlePrintSchedule() {
			JPanel pane = new JPanel();
	        pane.setLayout(new GridLayout());

	        JTextField Snamefield = new JTextField();
	        Snamefield.setSize(1, 1);

	        pane.add(new JLabel("Student Name:"));
	        pane.add(Snamefield);

	        int option = JOptionPane.showConfirmDialog(null, pane, "Print Student Schedule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	        if (option == JOptionPane.OK_OPTION) {
	        	String data;
	            String Sname = Snamefield.getText();
	            int flag = 0;
	            int i;
	            int j;
	            for( i = 0; i < uni.departmentList.size();i++) {
	            	for( j = 0; j < uni.departmentList.get(i).getStudentList().size();j++) {
	            		String A = uni.departmentList.get(i).getStudentList().get(j).getName();
	            		if(Sname.equals(A)) {
	            			flag = 1;
           					final int d = i;
        					final int s = j;
	            			final class SimpleWindow extends JFrame{
	            				
//	            				private class PrintStream{
//	            					
//	            				}
//	            				PrintStream ps = new PrintStream(uni.printAll());
	            				private SimpleWindow(){
	            					super(Sname+"'s Schedule");
	            					setLayout(new GridLayout());
	            					final int WINDOW_WIDTH = 500;
	            					final int WINDOW_HEIGHT = 500;
	            					setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	 
	            					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            					PrintStream ps = new PrintStream(baos);
	            					// IMPORTANT: Save the old System.out!
	            					PrintStream old = System.out;
	            					// Tell Java to use your special stream
	            					System.setOut(ps);
	            					// Print some output: goes to your special stream
	            					uni.departmentList.get(d).getStudentList().get(s).printSchedule();
	            					// Put things back
	            					System.out.flush();
	            					System.setOut(old);
	            					// Show what happened
	            					String data = baos.toString();
	            					//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            					JTextArea sampleTextArea = new JTextArea ();
	            					sampleTextArea.append(data);
	            					JScrollPane sampleScrollPane = new JScrollPane (sampleTextArea,
	            													JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            													JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	            					this.add(sampleScrollPane);
	            					setVisible(true);
	            				}
	            			}	 
	            			 SimpleWindow myWindow = new SimpleWindow();
        					

            	            break;
	            		}
	            		
	            	}
	            	if(flag == 1) {
	            		break;
	            	}
	            }
	            if(flag == 0) {
	            	pane = new JPanel();
    	            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

    	            pane.add(new JLabel("Student " + Sname +" doesnt exist"));
    	            JOptionPane.showMessageDialog(null, pane,"Error",JOptionPane.PLAIN_MESSAGE);

	            }
	        }
		}	
	}
}
