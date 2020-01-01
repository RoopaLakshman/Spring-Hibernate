package com.hibernate.eager.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class EagerLazyAfterSessionClosingDemo {

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

			int id = 10;

			// Hibernate Query with HQL
			Query<Instructor> query = session
					.createQuery(
							"Select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId",
							Instructor.class);
			query.setParameter("theInstructorId", id);

			// execute query
			Instructor instructor = query.getSingleResult();

			// Commit Transaction
			session.getTransaction().commit();

			System.out.println("Instructor fetched :: " + instructor);
			System.out.println("Courses -- " + instructor.getCourses());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}
}
