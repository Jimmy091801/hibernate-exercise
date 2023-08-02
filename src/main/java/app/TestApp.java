package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory =HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Member member =session.get(Member.class, 1);
		System.out.println(member.getNickname());	
		HibernateUtil.shutdown();
	}

}
