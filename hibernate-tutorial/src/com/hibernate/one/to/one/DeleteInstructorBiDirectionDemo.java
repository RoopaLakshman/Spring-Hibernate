package com.hibernate.one.to.one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class DeleteInstructorBiDirectionDemo {

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
			InstructorDetail instructorDetail = session.get(
					InstructorDetail.class, 7);

			System.out.println("Fetched instructor details -- "
					+ instructorDetail.toString());
			// Delete instructor
			/**
			 * This will delete it in memory. It will delete both instructor and
			 * instructor detail since we have cascaded the delete operation
			 **/
			
			/**It will fail since there is foreign key constraint. You can't just delete InstructorDetail leaving Instructor behind**/
			if (null != instructorDetail) {
				instructorDetail.getInstructor().setInstructorDetail(null);
				session.delete(instructorDetail);
			}

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
