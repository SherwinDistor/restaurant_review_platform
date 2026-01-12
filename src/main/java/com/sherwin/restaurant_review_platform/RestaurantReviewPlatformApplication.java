package com.sherwin.restaurant_review_platform;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Role;
import com.sherwin.restaurant_review_platform.repositories.RestaurantUserRepository;
import com.sherwin.restaurant_review_platform.repositories.RoleRepository;

@SpringBootApplication
public class RestaurantReviewPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReviewPlatformApplication.class, args);
	}

	@Value("${security.email}")
	private String email;

	@Value("${security.password}")
	private String password;

	@Bean
	CommandLineRunner run(RoleRepository roleRepository,
			RestaurantUserRepository restaurantUserRepository,
			PasswordEncoder passwordEncoder) {

		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) {
				return;
			}

			Role admin = new Role();
			admin.setAuthority("ADMIN");
			Role saveAdmin = roleRepository.save(admin);

			Role user = new Role();
			user.setAuthority("USER");
			roleRepository.save(user);

			Set<Role> roles = new HashSet<>();
			roles.add(saveAdmin);

			RestaurantUser restaurantUserAdmin = new RestaurantUser();
			restaurantUserAdmin.setUsername(email);
			restaurantUserAdmin.setPassword(passwordEncoder.encode(password));
			restaurantUserAdmin.setAuthorities(roles);
			restaurantUserAdmin.setCreatedAt(LocalDateTime.now());

			restaurantUserRepository.save(restaurantUserAdmin);

		};
	}
}
