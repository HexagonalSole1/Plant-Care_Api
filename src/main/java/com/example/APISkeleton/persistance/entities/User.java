package com.example.APISkeleton.persistance.entities;

import com.example.APISkeleton.persistance.entities.pivots.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private Character gender;

    @OneToMany(mappedBy = "user")
    private List<Device> devices;


    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
