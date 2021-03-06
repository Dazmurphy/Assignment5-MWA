package assign.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import assign.domain.Meeting;
import assign.domain.Project;
import junit.framework.TestCase;

public class TestDBLoader extends TestCase {

	DBLoader dbLoader;
	
	@Override
	protected void setUp() {
		dbLoader = new DBLoader();
	}
	
	@Test
	public void testProjectInsert() {
		try {
			Project p = new Project("p7", "girg");
			dbLoader.addProject(p);
			//System.out.println("Project ID:" + projectId);
			
			//Assignment assignment = dbLoader.getAssignment(title);
			//assertEquals(assignment.getTitle(), title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void testUpdate() {
	}
	
	@Test
	public void testAssignmentAndCourseInsert() {
		try {
			String title = "ETL";
			String courseTitle = "Modern Web Applications";
			int assignmentId = dbLoader.addAssignmentAndCourse(title, courseTitle);
			System.out.println("Assignment ID:" + assignmentId);
			
			Assignment assignment = dbLoader.getAssignment(title);
			assertEquals(assignment.getTitle(), title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultipleAssignments() {
		try {
		List<String> assignments = new ArrayList<String>();
		assignments.add("Memory Subsystem");
		assignments.add("Device Drivers");
		String courseTitle = "Operating Systems";
		int courseId = dbLoader.addAssignmentsToCourse(assignments, courseTitle);
		
		List<Assignment> a = dbLoader.getAssignmentsForACourse(courseId);
		
		System.out.println("Title: " + a.get(0).getTitle());
		System.out.println("Title: " + a.get(1).getTitle());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJoinQuery() {
		try {
			List<String> assignments = new ArrayList<String>();
			assignments.add("Memory Subsystem");
			assignments.add("Device Drivers");
			String courseTitle = "Operating Systems";
			dbLoader.addAssignmentsToCourse(assignments, courseTitle);
		
			List<Object[]> tuples = dbLoader.getAssignmentsForACourse(courseTitle);
		
			System.out.println("Size of assignment list:" + tuples.size());
		
			for(int i=0; i<tuples.size(); i++) {
				Object[] pair = tuples.get(i);
				UTCourse course = null;
				Assignment assignment = null;
			
				if (pair[0] instanceof Assignment && pair[1] instanceof UTCourse) {
					assignment = (Assignment)pair[0];
					course = (UTCourse)pair[1];
				} else if (pair[0] instanceof UTCourse && pair[1] instanceof Assignment) {
					assignment = (Assignment)pair[1];
					course = (UTCourse)pair[0];
				}
				System.out.println("Course:" + course.getCourseName() + " Assignment:" + assignment.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testAssignmentGetWithId() {
		try {
			String title = "Servlets";
			int assignmentId = dbLoader.addAssignment(title);
			System.out.println("Assignment ID:" + assignmentId);
			
			Assignment assignment = dbLoader.getAssignment(assignmentId);
			assertEquals(assignment.getTitle(), title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAssignmentDelete() {
		try {
			String title = "Assignment 1";
			dbLoader.addAssignment(title);
			
			Assignment assignment = dbLoader.getAssignment(title);
			assertEquals(assignment.getTitle(), title);
			
			dbLoader.deleteAssignment(title);
			
			Assignment deletedAssignment = dbLoader.getAssignment(title);
			
			assertNull(deletedAssignment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testDeleteCourseAndAssignments() {
		try {
			String title = "ETL";
			String courseTitle = "Modern Web Applications";
			int assignmentId = dbLoader.addAssignmentAndCourse(title, courseTitle);
			System.out.println("Assignment ID:" + assignmentId);
			
			Assignment assignment = dbLoader.getAssignment(title);
			assertEquals(assignment.getTitle(), title);
			
			dbLoader.deleteCourse(courseTitle);
			
			Assignment assignment1 = dbLoader.getAssignment(title);
			assertNull(assignment1);
			
			UTCourse c = dbLoader.getCourse(courseTitle);
			assertNull(c);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
