package test.my_app.repos;

import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.my_app.domain.Product;

import java.util.List;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.status LEFT JOIN FETCH p.subcate LEFT JOIN FETCH p.brands")
    List<Product> getAll();

}
