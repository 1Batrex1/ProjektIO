package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.edziekanat.io.dao.OcenaRepository;
import projekt.edziekanat.io.dao.PrzedmiotRepository;
import projekt.edziekanat.io.dao.StudentRepository;
import projekt.edziekanat.io.entites.Ocena;
import projekt.edziekanat.io.entites.Przedmiot;
import projekt.edziekanat.io.entites.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OcenaStudentController {

    StudentRepository studentRepository;

    OcenaRepository ocenaRepository;

    EntityManager entityManager;

    PrzedmiotRepository przedmiotRepository;

    @Autowired
    OcenaStudentController(OcenaRepository ocenaRepository, StudentRepository studentRepository,
                           EntityManager entityManager, PrzedmiotRepository przedmiotRepository)
    {
        this.ocenaRepository = ocenaRepository;
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
        this.przedmiotRepository = przedmiotRepository;
    }


    @GetMapping("/wyswietlOceny")
    public String wyswietlOceny(@RequestParam("przedmiotId") int theId, Model theModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student> student = studentRepository.findByOsoba_Id(Integer.valueOf(authentication.getName()));


        List<Ocena> ocena = ocenaRepository.findAllByStudent_IndexStudenta(student.get().getIndexStudenta());
        List<Ocena> ocenyPrzedmiotu = new ArrayList<>();

        for (Ocena val: ocena) {
            if (val.getPrzedmiot().getIdPrzedmiotu() == theId) {
                ocenyPrzedmiotu.add(val);
            }
        }

        theModel.addAttribute("listaOcen", ocenyPrzedmiotu);

        return "oceny";
    }

    @GetMapping("/wybierzPrzedmiot")
    public String wybierzPrzedmiot(Model theModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student> student = studentRepository.findByOsoba_Id(Integer.valueOf(authentication.getName()));

        List<Przedmiot> przedmioty = przedmiotRepository.findAllBySemestr(student.get().getSemestr());

        List<List<Ocena>> ocenyPrzedmiotu = new ArrayList<>();

        for (Przedmiot przedmiot: przedmioty) {
            ocenyPrzedmiotu.add(ocenaRepository.findAllByPrzedmiot_IdPrzedmiotuAndStudent_IndexStudenta(przedmiot.getIdPrzedmiotu(),
                    student.get().getIndexStudenta()));
        }


/*        TypedQuery<Przedmiot> theQuery = entityManager.createQuery("FROM Przedmiot", Przedmiot.class);
        theModel.addAttribute("listaPrzedmiotow", theQuery.getResultList());*/

        theModel.addAttribute("listaPrzedmiotow", przedmioty);
        theModel.addAttribute("listaOcen", ocenyPrzedmiotu);

        return "wybor-przedmiotu-do-wyswietlenia-ocen";
    }

}
