package com.hibernate.one.to.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class CreateInstructorBiDirectionDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = null;

		try {

			// Create objects
			InstructorDetail instructorDetail = new InstructorDetail(
					"http://youtube.com/uttara", "loves to code");

			Instructor instructor = new Instructor("Vikram", "Shastry",
					"vikram.shastry@gmail.com");

			// Associate Objects ----- Bi-Directional(Both set to each other)
			instructorDetail.setInstructor(instructor);
			instructor.setInstructorDetail(instructorDetail);

			// Get session
			session = factory.getCurrentSession();

			// Begin Transaction
			session.beginTransaction();

			// Save Instructor
			/** This will also save the detail object **/
			session.save(instructorDetail);

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
