package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekt.edziekanat.io.dao.*;
import projekt.edziekanat.io.entites.Grupa;
import projekt.edziekanat.io.entites.Wykladowca;
import projekt.edziekanat.io.entites.Zajecia;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OcenaWykladowcaController {
    WykladowcaRepository wykladowcaRepository;
    StudentRepository studentRepository;
    OcenaRepository ocenaRepository;
    EntityManager entityManager;

    ZajeciaRepository zajeciaRepository;

    @Autowired
    public OcenaWykladowcaController(WykladowcaRepository wykladowcaRepository,
                                     StudentRepository studentRepository,
                                     OcenaRepository ocenaRepository,
                                     ZajeciaRepository zajeciaRepository,
                                     EntityManager entityManager) {
        this.wykladowcaRepository = wykladowcaRepository;
        this.studentRepository = studentRepository;
        this.ocenaRepository = ocenaRepository;
        this.entityManager = entityManager;
        this.zajeciaRepository = zajeciaRepository;
    }


    @GetMapping("/wybierzGrupe")
    public String wybierzGrupe(Model theModel) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Wykladowca wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(Integer.parseInt(authentication.getName()));
        Integer idWykladowcy = wykladowca.getIndexWykladowcy();
        System.out.println(idWykladowcy);
        List<Zajecia> zajeciaList =  zajeciaRepository.findAllByWykladowca_IndexWykladowcy(idWykladowcy);

        List<Grupa> grupaList = new ArrayList<Grupa>();

        for (Zajecia tempZajecia: zajeciaList
             ) {
            grupaList.add(tempZajecia.getGrupa());
        }



        theModel.addAttribute("listaGrup", grupaList);

        return "wybor-grupy";

    }
}
