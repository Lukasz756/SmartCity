package com.example.smartcity.JobOffer;

import com.example.smartcity.User.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "joboffers")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joboffer_id")
    private Long id;
    private String title;
    private String description;
    private Date date;
    private boolean isAvailable;

    @ManyToMany
    @JoinTable(name = "user_jobsApplied",
            joinColumns = @JoinColumn(name = "joboffer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> candidates = new HashSet<User>();

    private Long employerId;

    @OneToOne
    @JoinColumn(name="acutal_user_id",referencedColumnName = "user_id")
    private User gotTheJob;

}
