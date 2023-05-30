package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.edziekanat.io.dao.*;
import projekt.edziekanat.io.entites.Ocena;
import projekt.edziekanat.io.entites.Student;
import projekt.edziekanat.io.entites.Wykladowca;
import projekt.edziekanat.io.entites.Zajecia;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OcenaWykladowcaController {
    WykladowcaRepository wykladowcaRepository;
    StudentRepository studentRepository;
    OcenaRepository ocenaRepository;

    ZajeciaRepository zajeciaRepository;
    PrzedmiotRepository przedmiotRepository;
    EntityManager entityManager;

    private int idPrzedmiotu;
    private int idOceny = 1;

    @Autowired
    public OcenaWykladowcaController(WykladowcaRepository wykladowcaRepository,
                                     StudentRepository studentRepository,
                                     OcenaRepository ocenaRepository,
                                     ZajeciaRepository zajeciaRepository,
                                     PrzedmiotRepository przedmiotRepository,
                                     EntityManager entityManager) {
        this.wykladowcaRepository = wykladowcaRepository;
        this.studentRepository = studentRepository;
        this.ocenaRepository = ocenaRepository;
        this.zajeciaRepository = zajeciaRepository;
        this.przedmiotRepository = przedmiotRepository;
        this.entityManager = entityManager;
    }


    @GetMapping("/wybierzZajecia")
    public String wybierzZajecia(Model theModel) {
        TypedQuery<Zajecia> theQuery = entityManager.createQuery("FROM Zajecia ", Zajecia.class);

        List<Zajecia> listaZajec = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Wykladowca wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(Integer.parseInt(authentication.getName()));
        int idWykladowcy = wykladowca.getIndexWykladowcy();

       for (Zajecia zajecia: theQuery.getResultList()) {
            if (zajecia.getWykladowca().getIndexWykladowcy() == idWykladowcy) {
                listaZajec.add(zajecia);
            }
        }

        theModel.addAttribute("listaZajec", listaZajec);

        return "wybor-zajec";
    }

    @GetMapping("/listaStudentow")
    public String listaStudentow(@RequestParam("idGrupy") int grupaId,
                                 @RequestParam("idPrzedmiotu") int przedmiotId,
                                 Model theModel) {
        List<Student> students = studentRepository.findAllByGrupa_IdGrupy(grupaId);
        theModel.addAttribute("listaStudentow", students);

        idPrzedmiotu = przedmiotId;

        return "lista-studentow";
    }


    // dodac przed jeszcze wyborem grupy wybor przedmiotu i dopiero zaimplementowac metode nizej
    @GetMapping("/formularzOceny")
    public String formularzOceny(@RequestParam("indexStudenta") int indexStudenta,
                              Model theModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int idWykladowcy = Integer.parseInt(authentication.getName());

        Ocena ocena = new Ocena();
        ocena.setId(idOceny);
        ocena.setStudent(studentRepository.findByIndexStudenta(indexStudenta).get());
        ocena.setPrzedmiot(przedmiotRepository.findByIdPrzedmiotu(idPrzedmiotu).get());
        ocena.setWykladowca(wykladowcaRepository.findWykladowcaByOsobaId(idWykladowcy));

        idOceny++;

        theModel.addAttribute("ocena", ocena);

        return "wystawianie-ocen";
    }

    @PostMapping("/wystawOcene")
    public String wystawOcene(@ModelAttribute("ocena") Ocena ocena) {

        // nie dziala dodawanie obiektu do bazy danych
        ocena.getStudent().getOcena().add(ocena);
        ocenaRepository.save(ocena);

        return "redirect:/wybierzZajecia";
    }
}
