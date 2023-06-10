package test.my_app.domain;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
public class Brand {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String brandName;


    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id",referencedColumnName = "id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"product_id", "brand_id"})
            }
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("brands")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Product> products = new HashSet<>();

    @Override
    public String toString() {
        return brandName;
    }

}
