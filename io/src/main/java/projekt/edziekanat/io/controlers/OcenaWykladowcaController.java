package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.edziekanat.io.dao.OcenaRepository;
import projekt.edziekanat.io.dao.StudentRepository;
import projekt.edziekanat.io.dao.WykladowcaRepository;
import projekt.edziekanat.io.entites.Grupa;
import projekt.edziekanat.io.entites.Student;
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

        for (Grupa grupa: theQuery.getResultList()) {
            if (grupa.getWykladowca().getIndexWykladowcy() == idWykladowcy) {
                listaGrup.add(grupa);
            }
        }

        List<Integer> roki = new ArrayList<>();
        for (Grupa grupa : listaGrup) {
            roki.add(Character.getNumericValue(Integer.toString(grupa.getIdGrupy()).toCharArray()[0]));
        }

        theModel.addAttribute("listaGrup", listaGrup);
        theModel.addAttribute("listaRokow", roki);

        return "wybor-grupy";
    }

    @GetMapping("/listaStudentow")
    public String listaStudentow(@RequestParam("idGrupy") int theId, Model theModel) {
        List<Student> student = studentRepository.findAllByIdGrupy(theId);
        theModel.addAttribute("listaStudentow", student);

        return "lista-studentow";
    }

    // dodac przed jeszcze wyborem grupy wybor przedmiotu i dopiero zaimplementowac metode nizej
    @PostMapping("/wystawOcene")
    public String wystawOcene(@RequestParam("idStudenta") int theId) {

        return "wystawianie-ocen";
    }
}
