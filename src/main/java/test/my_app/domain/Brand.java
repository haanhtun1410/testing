package test.my_app.domain;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")

public class Brand implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String brandName;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE,mappedBy = "brands")
    @JsonIgnoreProperties("brands")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Product> products;



}
