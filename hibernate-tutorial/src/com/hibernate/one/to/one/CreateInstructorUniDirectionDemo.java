package com.hibernate.one.to.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class CreateInstructorUniDirectionDemo {

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
					"http://youtube.com/madhu_patel", "guitar");

			Instructor instructor = new Instructor("Madhu", "Patel",
					"madhu.patel@gmail.com", instructorDetail);

			// Associate Objects
			/** I'm passing the detail object to constructor of instructor only **/

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
