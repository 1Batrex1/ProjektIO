package projekt.edziekanat.io.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

@Service
public class OcenaServiceImpl implements OcenaService {

    EntityManager entityManager;

    public OcenaServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ocena> findAllByStudentId(int theId) {
        TypedQuery<Ocena> query = entityManager.createQuery("FROM Ocena WHERE student.indexStudenta=:theData", Ocena.class);
        query.setParameter("theData", theId);

        return query.getResultList();
    }
}
