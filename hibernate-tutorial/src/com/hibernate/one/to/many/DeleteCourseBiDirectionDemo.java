package com.hibernate.one.to.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class DeleteCourseBiDirectionDemo {

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

			// get instructor from DB
			int courseId = 4;
			Course course = session.get(Course.class, courseId);

			System.out.println("Course to be deleted : "+course);
			session.delete(course);
			// Commit Transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
