package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekt.edziekanat.io.dao.OcenaRepository;
import projekt.edziekanat.io.dao.StudentRepository;
import projekt.edziekanat.io.dao.WykladowcaRepository;
import projekt.edziekanat.io.entites.Grupa;
import projekt.edziekanat.io.entites.Wykladowca;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OcenaWykladowcaController {
    WykladowcaRepository wykladowcaRepository;
    StudentRepository studentRepository;
    OcenaRepository ocenaRepository;
    EntityManager entityManager;

    @Autowired
    public OcenaWykladowcaController(WykladowcaRepository wykladowcaRepository,
                                     StudentRepository studentRepository,
                                     OcenaRepository ocenaRepository,
                                     EntityManager entityManager) {
        this.wykladowcaRepository = wykladowcaRepository;
        this.studentRepository = studentRepository;
        this.ocenaRepository = ocenaRepository;
        this.entityManager = entityManager;
    }


    @GetMapping("/wybierzGrupe")
    public String wybierzGrupe(Model theModel) {
        TypedQuery<Grupa> theQuery = entityManager.createQuery("FROM Grupa", Grupa.class);

        List<Grupa> listaGrup = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Wykladowca wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(Integer.parseInt(authentication.getName()));
        int idWykladowcy = wykladowca.getIndexWykladowcy();
        System.out.println(idWykladowcy);

        for (Grupa grupa: theQuery.getResultList()) {
            System.out.println(grupa.getWykladowca().getIndexWykladowcy());
            System.out.println(idWykladowcy);
            if (grupa.getWykladowca().getIndexWykladowcy() == idWykladowcy) {
                listaGrup.add(grupa);
            }

        }

        System.out.println(listaGrup);
        theModel.addAttribute("listaGrup", listaGrup);

        return "wybor-grupy";

    }
}
