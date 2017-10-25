//package com.mycompany.myproject.dao;
//
//import com.mycompany.myproject.persist.entity.user.User;
//import org.hibernate.Criteria;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public class UserDaoImpl  implements UserDao {
//
//	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
//
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	@Override
//	public User findById(int id) {
//		return (User) sessionFactory.getCurrentSession().get(User.class,id);
//	}
//
//	@Override
//	public User findBySSO(String sso) {
//		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
//		crit.add(Restrictions.eq("username", sso));
//		User user = (User)crit.uniqueResult();
//		return user;
//	}
//
//	@Override
//	public void save(User user) {
//
//	}
//
//	@Override
//	public void deleteBySSO(String sso) {
//
//	}
//
//	@Override
//	public List<User> findAllUsers() {
//		return null;
//	}
//}
