package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
