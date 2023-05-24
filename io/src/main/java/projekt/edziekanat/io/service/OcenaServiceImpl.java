package projekt.edziekanat.io.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

public class OcenaServiceImpl implements OcenaService {

    EntityManager entityManager;

    @Override
    public List<Ocena> findAllByStudentId(int theId) {
        TypedQuery<Ocena> query = entityManager.createQuery("FROM Ocena WHERE student.id=:theData", Ocena.class);
        query.setParameter("theData", theId);

        return query.getResultList();
    }
}
