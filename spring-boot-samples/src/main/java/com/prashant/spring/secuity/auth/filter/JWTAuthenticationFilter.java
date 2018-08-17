package com.prashant.spring.secuity.auth.filter;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.EXPIRATION_TIME;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.HEADER_STRING;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.SECRET;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashant.spring.common.model.ApplicationUser;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response)
			throws AuthenticationException {

		logger.info("Attempting Authentication by User");
		Authentication authentication = null;

		try {
			ApplicationUser credentials = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);
			logger.info("Starting authenticaion for user :" + credentials.getUserName());
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credentials.getUserName(), credentials.getPassword(), new ArrayList<>()));
		} catch (JsonParseException | JsonMappingException e) {

			logger.error("Exception occurred at parsing JSON ", e);

		} catch (IOException e) {
			logger.error("Exception occurred at attempting authentication ", e);
		}

		logger.info("returning from attemptAuthetication method  authetication : " + authentication.isAuthenticated());
		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain,
			Authentication auth) throws ServletException, IOException {
		logger.info("Initiated Successful authentication method. Is user autheticated ? " + auth.isAuthenticated());
		String jwtToken = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
		logger.info("Generated JWT Token is " + jwtToken);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

	}

}
