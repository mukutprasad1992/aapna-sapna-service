package com.aapnasapna.AapnaSapna.dao;

import com.aapnasapna.AapnaSapna.models.UserInfo;

public interface IUserInfoDAO {
	UserInfo addUser(UserInfo user);

	UserInfo getActiveUser(String userName);
}