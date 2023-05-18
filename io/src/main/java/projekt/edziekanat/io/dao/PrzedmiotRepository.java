package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Przedmiot;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot,Integer> {
}
