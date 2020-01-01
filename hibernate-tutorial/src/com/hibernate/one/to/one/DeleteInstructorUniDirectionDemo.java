package com.hibernate.one.to.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class DeleteInstructorUniDirectionDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = null;

		try {

			// Get session
			session = factory.getCurrentSession();

			// Begin Transaction
			session.beginTransaction();

			// Get instructor
			Instructor instructor = session.get(Instructor.class, 5);

			// Delete instructor
			/**
			 * This will delete it in memory. It will delete both instructor and
			 * instructor detail since we have cascaded the delete operation
			 **/
			if (null != instructor)
				session.delete(instructor);

			// Commit Transaction
			/**
			 * This will delete it in DB. It will delete both instructor and
			 * instructor detail since we have cascaded the delete operation
			 **/
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
