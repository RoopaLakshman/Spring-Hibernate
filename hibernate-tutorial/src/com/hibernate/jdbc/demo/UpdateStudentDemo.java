package com.hibernate.jdbc.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = null;

		try {
			// Get student
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> bsStudents = session.createQuery(
					"from Student s where s.lastName = 'BS'").getResultList();
			// Update the last name. This will only update it in memory
			for (Student bs : bsStudents) {
				bs.setLastName("Basavaraju");
			}

			// Commit it. The update in memory which is a "persistent object" is
			// now updated to DB
			session.getTransaction().commit();

			// Update many objects at a time.
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery(
					"Update Student set email = 'foo@gmail.com' where lastName = 'Lakshmanaiah'")
					.executeUpdate();
			/**
			 * If you do not include the where class here, it will update all
			 * rows.
			 **/

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Hey in catch block : ");
			e.printStackTrace();
		} finally {
			System.out.println("Hey in final");
			session.close();
			factory.close();
		}
	}
}
