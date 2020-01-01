package com.hibernate.one.to.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class CreateInstructorBiDirectionDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = null;

		try {

			// Create objects
			InstructorDetail instructorDetail = new InstructorDetail(
					"http://youtube.com/susanfoster", "Video Games");

			Instructor instructor = new Instructor("Susan", "Foster",
					"susan.foster@gmail.com", instructorDetail);

			// Get session
			session = factory.getCurrentSession();

			// Begin Transaction
			session.beginTransaction();

			// Save Instructor
			/** This will also save the detail object **/
			session.save(instructor);

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
