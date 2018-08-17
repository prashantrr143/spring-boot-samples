package com.prashant.spring.secuity.auth.filter;

import static com.prashant.spring.secuity.auth.filter.SecurityConstants.HEADER_STRING;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.SECRET;
import static com.prashant.spring.secuity.auth.filter.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		logger.info("Initializing JWTAuthorizationFilter");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("Started internal filter authenticaiton method");
		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			logger.info("header is null or token prefix not found " + header);
			chain.doFilter(req, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, response);
		logger.info("Returning ------->");
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {

		logger.info("started checking for  Autherication ");
		String token = req.getHeader(HEADER_STRING);
		if (token != null) {
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();
			logger.info("Retreived user from request is  " + user);
			if (user != null) {
				logger.info("user authenticated successfully");
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			return null;
		}

		return null;
	}
}
