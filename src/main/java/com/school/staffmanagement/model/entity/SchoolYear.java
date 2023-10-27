package com.school.staffmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "school_year")
public class SchoolYear implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;

    private String division;

    private String shift;

    private String title;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;
}
