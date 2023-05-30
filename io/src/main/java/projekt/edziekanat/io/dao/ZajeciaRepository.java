package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Zajecia;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ZajeciaRepository extends JpaRepository<Zajecia,Integer> {

    List<Zajecia> findAllByWykladowca_IndexWykladowcy(Integer integer);
    Optional<Zajecia> findByWykladowca_IndexWykladowcyAndDataZajecEqualsAndGodzinaZajecEquals(Integer integer, Date date, Time time);

    Optional<Zajecia> findByBudynekSala_Id_BudynekidBudynkuAndBudynekSala_Id_SalaidSaliAndDataZajecEqualsAndGodzinaZajecEquals(Integer integer1,Integer integer2,Date date,Time time);

    Optional<Zajecia> findByGrupa_IdGrupyAndDataZajecEqualsAndGodzinaZajecEquals(Integer integer, Date date, Time time);

}
