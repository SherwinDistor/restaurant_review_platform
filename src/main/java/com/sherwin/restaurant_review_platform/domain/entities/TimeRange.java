package com.sherwin.restaurant_review_platform.domain.entities;

import java.util.UUID;

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
public class TimeRange {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private DayOfWeek dayOfWeek;

    private String openingTime;
    private String closingTime;

}
