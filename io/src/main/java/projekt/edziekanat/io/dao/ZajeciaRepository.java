package projekt.edziekanat.io.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projekt.edziekanat.io.entites.Grupa;
import projekt.edziekanat.io.entites.Zajecia;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ZajeciaRepository extends JpaRepository<Zajecia,Integer> {

    List<Zajecia> findAllByWykladowca_IndexWykladowcy(Integer integer);
    Optional<Zajecia> findByWykladowca_IndexWykladowcyAndDataZajecEqualsAndGodzinaZajecEquals(Integer integer, Date date, Time time);

    Optional<Zajecia> findByBudynekSala_Id_BudynekidBudynkuAndBudynekSala_Id_SalaidSaliAndDataZajecEqualsAndGodzinaZajecEquals(long budynekSala_id_budynekidBudynku, long budynekSala_id_salaidSali, Date dataZajec, Time godzinaZajec);

    Optional<Zajecia> findByGrupa_IdGrupyAndDataZajecEqualsAndGodzinaZajecEquals(Integer integer, Date date, Time time);



    List<Zajecia> findDistinctByWykladowca_IndexWykladowcy(Integer integer1);


    List<Zajecia> findAllByPrzedmiot(Integer integer);

    List<Zajecia> findAllByPrzedmiotAndGrupa(Integer integer1, Integer integer2);
}
