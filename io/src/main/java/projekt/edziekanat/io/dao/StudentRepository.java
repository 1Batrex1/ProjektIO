package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByOsoba_Id(Integer integer);

    List<Student> findAllByGrupa(Integer integer);

    List<Student> findAllByGrupa_IdGrupy(Integer grupaId);

    Optional<Student> findByIndexStudenta(Integer indexStudenta);
}
