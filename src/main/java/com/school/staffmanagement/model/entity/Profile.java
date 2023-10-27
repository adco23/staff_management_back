package com.school.staffmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "profile")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fist_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String cuil;
    private String address;
    private Date birthdate;
    private String phone;
    private String degree;
    @Column(name = "degree_reg")
    private String degreeReg;
    @Column(name = "medical_condition")
    private String medicalCondition;
    @Column(name = "emergency_person")
    private String emergencyPerson;
    @Column(name = "emergency_phone")
    private String emergencyPhone;
}
