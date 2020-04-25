package com.aapnasapna.AapnaSapna.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aapnasapna.AapnaSapna.models.UserInfo;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserInfo addUser(UserInfo user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public UserInfo getActiveUser(String userName) {
		UserInfo activeUserInfo = new UserInfo();
		short enabled = 1;
		List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=?1 and enabled=?2")
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		if (!list.isEmpty()) {
			activeUserInfo = (UserInfo) list.get(0);
		}
		return activeUserInfo;
	}
}