package test.my_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.my_app.domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>  {


}
