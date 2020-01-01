package com.hibernate.eager.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class EagerLazyBeforeSessionClosingDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = null;

		try {

			// Get session
			session = factory.getCurrentSession();

			// Begin Transaction
			session.beginTransaction();

			// Get objects
			Instructor instructor = session.get(
					Instructor.class, 10);

			System.out.println("Session get is done");
			if (null != instructor)
				
				System.out.println("Instructor == "
						+ instructor.toString());
			

			System.out.println("Instructor Details == "
					+ instructor.getInstructorDetail().toString());

			// Commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Before calling courses");
			System.out.println("Courses == "
					+ instructor.getCourses());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}
}
