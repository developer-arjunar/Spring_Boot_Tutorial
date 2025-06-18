package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);

			readStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {

		// Create the Student Object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Arjuna", "Randeniya", "arjunarandeniya@email.com");

		// Save the Student Object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display id of the saved Student
		System.out.println("Saved student. Generated Id : " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// Create Multiple Students
		System.out.println("Creating 3 Student Objects...");
		Student tempStudent1 = new Student("Gihan", "Perera", "gihanperera@email.com");
		Student tempStudent2 = new Student("Lakshan", "Chalaka", "lakshanchalaka@email.com");
		Student tempStudent3 = new Student("Sachith", "Madushan", "sachithmadushan@email.com");

		// Save the Student Objects
		System.out.println("Saving the Student Objects...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void readStudent(StudentDAO studentDAO) {

		// Create Student Object
		System.out.println("Creating new Student Object...");
		Student tempStudent = new Student("Hasitha", "Jayawardhana", "hasithajayawardhana@email.com");

		// Save the Student
		System.out.println("Saving the Student...");
		studentDAO.save(tempStudent);

		// Display ID of the Saved Student
		int theId = tempStudent.getId();
		System.out.println("Student Saved. Generated ID: " + theId);

		// Retrieve Student based on the id: Primary Key
		System.out.println("Retrieving Student with ID: " + theId);
		Student myStudent =  studentDAO.findById(theId);

		// Display Student
		System.out.println("Found the Student: " + myStudent);
	}

}
