package org.bnss.repository;

import java.util.List;

import org.bnss.domain.Data;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataCustomRepositoryImpl implements DataCustomRepository {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Data> getAllFiles() {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("SELECT d from org.bnss.domain.Data d");
		return q.list();
	}
	

}
