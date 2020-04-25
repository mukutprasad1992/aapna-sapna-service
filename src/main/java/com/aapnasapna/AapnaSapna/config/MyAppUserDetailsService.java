package com.aapnasapna.AapnaSapna.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aapnasapna.AapnaSapna.dao.IUserInfoDAO;
import com.aapnasapna.AapnaSapna.models.UserInfo;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserInfoDAO userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo activeUserInfo = userInfoDAO.getActiveUser(userName);
		if (activeUserInfo == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = new User(activeUserInfo.getUserName(), activeUserInfo.getPassword(),
				Arrays.asList(authority));
		return userDetails;
	}

	public User register(UserInfo user) {
		UserInfo users = userInfoDAO.addUser(user);
		if (users == null) {
			throw new UsernameNotFoundException("User has not been created with this username: " + user.getUserName());
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		User userDetails = new User(user.getUserName(), user.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}