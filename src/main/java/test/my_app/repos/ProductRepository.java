package test.my_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.my_app.domain.Brand;
import test.my_app.domain.Product;

import java.util.List;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllBySubcateId(Long id);



    List<Product> findAllByBrands(Brand brand);


}
