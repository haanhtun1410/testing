package test.my_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.my_app.domain.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    Register findByPasswordAndEmail(String pw, String email);
}