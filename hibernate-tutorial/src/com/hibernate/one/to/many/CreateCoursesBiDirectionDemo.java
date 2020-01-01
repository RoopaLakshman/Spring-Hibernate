package com.hibernate.one.to.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class CreateCoursesBiDirectionDemo {

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
			int instructorId = 10;
			Instructor instructor = session.get(Instructor.class, instructorId);

			// create some course
			Course course1 = new Course("Spring and Hibernate");
			Course course2 = new Course("Java and Javascript");

			// add courses to instructor or Associate Objects
			instructor.add(course1);
			instructor.add(course2);

			// save the courses
			session.save(course1);
			session.save(course2);

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
