package com.sherwin.restaurant_review_platform.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherwin.restaurant_review_platform.domain.entities.Role;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByAuthority(String authority);
}
