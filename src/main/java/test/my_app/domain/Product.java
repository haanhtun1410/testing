package test.my_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.FetchStyle;
import org.hibernate.sql.results.graph.FetchStyleAccess;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
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


    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id",referencedColumnName = "id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"product_id", "brand_id"})
            }
    )
    private Set<Brand> brands = new HashSet<Brand>();

    public void setBrand(Brand brand) {
        brands.add(brand);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", color='" + color  +'\'' +
                ", quantity=" + quantity +'\'' +
                ", sellPrice=" + sellPrice +'\'' +
                ", originPrice=" + originPrice +'\'' +
                ", description='" + description ;
    }

    public String display() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", color='" + color  +'\'' +
                ", quantity=" + quantity +'\'' +
                ", sellPrice=" + sellPrice +'\'' +
                ", Brands=" + getBrands() +'\'' +
                ", subcate=" + subcate.getSubCateName() +'\'' +
                ", status=" + status +'\'' +
                ", description='" + description
                ;
    }
}
