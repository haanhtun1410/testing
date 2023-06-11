package test.my_app.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String productName;

    @Column(length = 50)
    private String color;

    @Column
    private Long quantity;

    @Column
    private Double sellPrice;

    @Column
    private Double originPrice;

    @Column(name = "description", length = 100)
    private String description;

    @JoinColumn(name = "status_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcate_id", nullable = false)
    private SubCategory subcate;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("products")
    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id",referencedColumnName = "id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"product_id", "brand_id"})
            }
    )
    private Set<Brand> brands ;
    public void setBrand(Brand brand) {
        brands.add(brand);
    }

}
