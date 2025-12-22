package com.sherwin.restaurant_review_platform;

import java.util.HashSet;
import java.util.Set;

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
			restaurantUserAdmin.setUsername("admin");
			restaurantUserAdmin.setPassword(passwordEncoder.encode("password"));
			restaurantUserAdmin.setAuthorities(roles);

			restaurantUserRepository.save(restaurantUserAdmin);

		};
	}
}
