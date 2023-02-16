package com.example.smartcity.User;

import com.example.smartcity.JobOffer.JobOffer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    private String username;
    private Date accountCreated;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "candidates")
    private Set<JobOffer> jobApplied;

    @OneToOne
    private JobOffer actualJob;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //automatically generated alt insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<JobOffer> getJobApplied() {
        return jobApplied;
    }

    public void setJobApplied(Set<JobOffer> jobApplied) {
        this.jobApplied = jobApplied;
    }

    public JobOffer getActualJob() {
        return actualJob;
    }

    public void setActualJob(JobOffer actualJob) {
        this.actualJob = actualJob;
    }
}
