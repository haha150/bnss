package org.bnss.repository;

import java.util.List;

import org.bnss.domain.RadCheck;
import org.bnss.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<User> getAllUsers() {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("SELECT u from org.bnss.domain.User u");
		return q.list();
	}

	@Override
	public List<RadCheck> getAllRad() {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("SELECT u from org.bnss.domain.RadCheck u");
		return q.list();
	}
	

}
