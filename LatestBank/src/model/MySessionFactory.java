package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class MySessionFactory {
	private static SessionFactory sf=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	private static Session session;
	private MySessionFactory()
	{}
	public static Session getInstance()
	{
		if(session==null)
			session=sf.openSession();
		return session;
	}
	
}
