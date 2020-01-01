package com.hibernate.jdbc.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = null;

		try {
			// Save student
			Student s = new Student("Vasant", "Sakre",
					"Vasant.sakre@yahoo.com", new Date());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
			/** This session ends here. It is not alive after this. **/
			System.out.println("Id of the student -- " + s.getId());

			// Get student
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student myStudent = session.get(Student.class, s.getId());
			session.getTransaction().commit();
			System.out.println("Student details - " + myStudent);

		} catch (Exception e) {
			System.out.println("Hey in catch block : ");
			e.printStackTrace();
		} finally {
			System.out.println("Hey in final");
			session.close();
			factory.close();
		}
	}

}
