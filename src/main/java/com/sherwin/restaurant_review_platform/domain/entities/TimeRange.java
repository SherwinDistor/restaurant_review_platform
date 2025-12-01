package com.sherwin.restaurant_review_platform.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private DayOfWeek dayOfWeek;

    private String openingTime;
    private String closingTime;

    // @ManyToOne
    // @JoinColumn(name = "operating_hours_id")
    // private OperatingHours operatingHours;
}
