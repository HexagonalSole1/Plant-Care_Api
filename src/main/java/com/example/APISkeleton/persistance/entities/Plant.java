package com.example.APISkeleton.persistance.entities;

import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameScientific;

    private int humidityEarth;
    private int humidityEnvironment;
    private int brightness;
    private int ambientTemperature;
    private int mq135;

    private String urlImagePlant;

    @OneToMany(mappedBy = "plant")
    private List<Device> devices;  // Relación con Device

    @ManyToMany
    @JoinTable(
            name = "plant_category",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "plant_type",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    @JsonManagedReference
    private Set<TypePlant> types;

    @ManyToMany
    @JoinTable(
            name = "plant_family",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "family_id")
    )
    @JsonManagedReference
    private Set<Family> families;
}
