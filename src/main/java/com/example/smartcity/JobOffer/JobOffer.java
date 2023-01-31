package com.example.smartcity.JobOffer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobOffer {
    @Id
    @GeneratedValue
    @Column(name = "joboffer_id")
    private Long id;

    private String description;
    private Date date;

}
