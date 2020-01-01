package com.hibernate.eager.lazy;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.jdbc.entity.Course;
import com.hibernate.jdbc.entity.Instructor;
import com.hibernate.jdbc.entity.InstructorDetail;

public class LazyLoadingCourseLoadDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			//
			// THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM

			// YOU NEED TO GET A NEW SESSION
			//

			System.out.println("\n\nluv2code: Opening a NEW session \n");

			session = factory.getCurrentSession();

			session.beginTransaction();
			
			int theId = 10;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			// get courses for a given instructor
			Query<Course> query = session.createQuery("select c from Course c "
					+ "where c.instructor.id=:theInstructorId", Course.class);

			query.setParameter("theInstructorId", theId);

			List<Course> tempCourses = query.getResultList();

			System.out.println("tempCourses: " + tempCourses);

			// now assign to instructor object in memory
			tempInstructor.setCourses(tempCourses);

			System.out.println("luv2code: Courses: "
					+ tempInstructor.getCourses());

			session.getTransaction().commit();

			System.out.println("luv2code: Done!");
		} finally {

			// add clean up code
			session.close();

			factory.close();
		}
	}
}
