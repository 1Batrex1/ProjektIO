package projekt.edziekanat.io.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Override
    Optional<Student> findById(Integer integer);
    Optional<Student> findByOsoba_Id(Integer integer);
}
