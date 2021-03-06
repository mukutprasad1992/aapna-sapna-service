package com.aapnasapna.AapnaSapna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aapnasapna.AapnaSapna.config.JwtTokenUtil;
import com.aapnasapna.AapnaSapna.config.MyAppUserDetailsService;
import com.aapnasapna.AapnaSapna.models.JwtRequest;
import com.aapnasapna.AapnaSapna.models.JwtResponse;
import com.aapnasapna.AapnaSapna.models.UserInfo;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private MyAppUserDetailsService myAppUserDetailsService;

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());

		final UserDetails userDetails = myAppUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("register")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserInfo user) throws Exception {
		UserInfo newUser = new UserInfo();
		newUser.setUserName(user.getUserName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setFullName(user.getFullName());
		newUser.setCountry(user.getCountry());
		newUser.setEnabled(user.getEnabled());
		newUser.setRole(user.getRole());
		// Add comment
		return ResponseEntity.ok(myAppUserDetailsService.register(newUser));
	}
}