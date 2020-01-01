package com.hibernate.one.to.many.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;
import com.hibernate.jdbc.entity.Review;

public class OneToManyUniDirectional {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int instructorId = 10;
			Instructor instructor = session.get(Instructor.class, instructorId);
			System.out.println("Instructor ----- "+instructor.toString());
			Course course = new Course("Microservices");
			Review review = new Review("Average. Should cover more lab sessions");
			course.add(review);
			
			instructor.add(course);
			
			//session.save(review);
			session.save(course);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}