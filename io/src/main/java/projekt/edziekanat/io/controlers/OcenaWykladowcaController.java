package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
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
    private int indexStudenta;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Wykladowca wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(Integer.parseInt(authentication.getName()));
        int idWykladowcy = wykladowca.getIndexWykladowcy();
        List<Zajecia> zajeciaList = zajeciaRepository.findDistinctByWykladowca_IndexWykladowcy(idWykladowcy);
        for (int i = 0; i < zajeciaList.size(); i++) {
            for (int j = 0; j < zajeciaList.size(); j++) {
                if (zajeciaList.get(i) == zajeciaList.get(j) && zajeciaList.size() > 1) {
                    zajeciaList.remove(j);
                }
            }
        }


        theModel.addAttribute("listaZajec", zajeciaList);

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

    @GetMapping("/formularzOceny")
    public String formularzOceny(@RequestParam("indexStudenta") int indexStudenta,
                              Model theModel) {

        this.indexStudenta = indexStudenta;

        Ocena ocena = new Ocena();
        ocena.setStudent(studentRepository.findByIndexStudenta(indexStudenta).get());
        ocena.setPrzedmiot(przedmiotRepository.findByIdPrzedmiotu(idPrzedmiotu).get());

        theModel.addAttribute("ocena", ocena);

        return "wystawianie-ocen";
    }

    @PostMapping("/wystawOcene")
    public String wystawOcene(@ModelAttribute("ocena") Ocena ocena) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int idWykladowcy = Integer.parseInt(authentication.getName());

        ocena.setStudent(studentRepository.findByIndexStudenta(indexStudenta).get());
        ocena.setPrzedmiot(przedmiotRepository.findByIdPrzedmiotu(idPrzedmiotu).get());
        ocena.setWykladowca(wykladowcaRepository.findWykladowcaByOsobaId(idWykladowcy));

        ocenaRepository.save(ocena);

        return "redirect:/wybierzZajecia";
    }
}
