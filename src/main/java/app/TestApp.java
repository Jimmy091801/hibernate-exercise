package app;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		Dept dept = session.get(Dept.class, 30);
//		var emps = dept.getEmps();
//		for (var emp : emps) {
//			System.out.println(emp.getEname());
//		}

//		Emp emp = session.get(Emp.class, 7369);
//		Dept dept = emp.getDept();
//		System.out.println(dept.getDeptno());
//		System.out.println(dept.getDname());

		Emp emp = session.get(Emp.class, 7369);
		Dept dept = emp.getDept();
		List<Emp> emps = dept.getEmps();
		for (Emp tmp : emps) {
			System.out.println(tmp.getEname());
		}

//		CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
//		CriteriaQuery<Member> criteriaQuery=criteriaBuilder.createQuery(Member.class);
//		//FROM MEMBER
//		Root<Member> root=criteriaQuery.from(Member.class);
//		//where USERNAME=? and PASSWORD=?
//		criteriaQuery.where(criteriaBuilder.and(
//				criteriaBuilder.equal(root.get("username"),"admin"),
//				criteriaBuilder.equal(root.get("password"),"P@ssw0rd")
//				));
//		//select USERNAME,NICKNAME
//		criteriaQuery.multiselect(root.get("username"),root.get("nickname"));
//		
//		//order by ID
//		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//		
//		Member member=session.createQuery(criteriaQuery).uniqueResult();
//		System.out.println(member.getNickname());

//		Member member = new Member();
//		TestApp app = new TestApp();
		// Test
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		app.insert(member);
//		System.out.println(member.getId());
//		member.setId(1);
//		member.setPass(true);
//		member.setRoleId(1);
//		System.out.println(app.deleteById(3));
//		System.out.println(app.updateById(member));
//		System.out.println(app.seleteById(2).getNickname());
//		app.seleteAll().forEach(member -> System.out.println(member.getNickname()));
//		//or
//		for(Member member:app.seleteAll()) {
//			System.out.println(member.getNickname());
//		}
	}

	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public int updateById(Member newMember) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldMember = session.get(Member.class, newMember.getId());
			// 如果有傳pass,就將此pass設定給oldMember
			final Boolean pass = newMember.getPass();
			if (pass != null) {
				oldMember.setPass(pass);
			}
			// 若有傳roleId,就將此roleId設定給oldMember
			final Integer roleId = newMember.getRoleId();
			if (roleId != null) {
				oldMember.setRoleId(roleId);
			}

			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public Member seleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			transaction.commit();
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public List<Member> seleteAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query = session
					.createQuery("SELECT new web.member.pojo.Member(username,nickname) FROM Member", Member.class);
			List<Member> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}
