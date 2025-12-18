package com.sherwin.restaurant_review_platform.domain.entities;

import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String authority;

  @Override
  public @Nullable String getAuthority() {
    return this.authority;
  }

}
