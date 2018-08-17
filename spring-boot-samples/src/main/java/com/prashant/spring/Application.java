package com.prashant.spring;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashant.spring.common.model.ApplicationUser;
import com.prashant.spring.common.model.Item;
import com.prashant.spring.common.model.Order;
import com.prashant.spring.repository.ApplicationUserRepository;
import com.prashant.spring.repository.OrderRepository;

@SpringBootApplication
@EnableCaching // Cache Support Enabled
@EnableJpaRepositories
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ApplicationUserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public org.springframework.http.converter.json.MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			ObjectMapper objectMapper) {
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}
	
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			logger.info("Stated CommandLiner Execution");
			logger.info("Current Orders count is  " + orderRepository.count());

			Order order = new Order();
			order.setName("Demo Order Name");

			Item item1 = new Item();
			item1.setAmount(new BigDecimal("45.00"));
			item1.setName("Demo Item 1");

			Item item2 = new Item();
			item2.setAmount(new BigDecimal("55.00"));
			item2.setName("Demo Item 2");

			order.addItem(item1);
			order.addItem(item2);
			order = orderRepository.save(order);
			logger.info("order saved in the database " + order.getOrderId());
			
			logger.info("Registering user in the application");
			
			ApplicationUser user = new ApplicationUser();
			user.setUserName("prashantsingh");
			user.setPassword(passwordEncoder.encode("password"));
			userRepository.save(user);
			
			logger.info("User Saved in to database");;
			ApplicationUser appUser = userRepository.findByUserName("prashantsingh");
			logger.info("Retrieved user is " + appUser);
			
			
			
			
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
		};
	}
}
