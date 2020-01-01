package com.hibernate.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = null;

		try {
			// Get student
			int id = 7;
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student s = session.get(Student.class, id);

			// Deletes in memory
			if (null != s) {
				session.delete(s);
			}

			// Deletes it in DB
			session.getTransaction().commit();

			// Without getting student
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Student where id = 15")
					.executeUpdate();
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
