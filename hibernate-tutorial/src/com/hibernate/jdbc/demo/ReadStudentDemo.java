package com.hibernate.jdbc.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = null;

		try {

			// Get all students
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student")
					.getResultList();
			session.getTransaction().commit();
			displayStudents(students);

			// Get students whose lastName is Nagaraj
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> nagarajStudents = session.createQuery(
					"from Student s where s.lastName = 'Nagaraj'")
					.getResultList();
			session.getTransaction().commit();
			displayStudents(nagarajStudents);

			// Get students whose lastName is Nagaraj or firstName is Roopa
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> nagarajOrRoopaStudents = session
					.createQuery(
							"from Student s where s.lastName = 'Nagaraj' OR s.firstName = 'Roopa'")
					.getResultList();
			session.getTransaction().commit();
			displayStudents(nagarajOrRoopaStudents);

			// Get students whose email id is gmail
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> gmailStudents = session.createQuery(
					"from Student s where s.email like '%@gmail.com'")
					.getResultList();
			session.getTransaction().commit();
			displayStudents(gmailStudents);

		} catch (Exception e) {
			System.out.println("Hey in catch block : ");
			e.printStackTrace();
		} finally {
			System.out.println("Hey in final");
			session.close();
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student s : students) {
			System.out.println("Student details - " + s);
		}
	}

}
