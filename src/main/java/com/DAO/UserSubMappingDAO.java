package com.DAO;

import org.hibernate.Session;

public class UserSubMappingDAO {
	
	
	public void addSubjects(Session session, UserSubMapping bean) {
		System.out.println("inside user");
		UserSubMapping u1=new UserSubMapping(bean.getRollno(),bean.getCoursename(),bean.getSubname());
		session.save(u1);
	}

}
