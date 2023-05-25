package projekt.edziekanat.io.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekt.edziekanat.io.dao.OcenaRepository;
import projekt.edziekanat.io.dao.StudentRepository;
import projekt.edziekanat.io.entites.Ocena;
import projekt.edziekanat.io.entites.Student;

import java.util.List;
import java.util.Optional;

@Controller
public class OcenaController {

    StudentRepository studentRepository;

    OcenaRepository ocenaRepository;
    @Autowired
    OcenaController(OcenaRepository ocenaRepository,StudentRepository studentRepository)
    {
        this.ocenaRepository = ocenaRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping("/sprawdz-oceny")
    public String wyswietlOceny(Model theModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student> student = studentRepository.findByOsoba_Id(Integer.valueOf(authentication.getName()));
        List<Ocena> ocena = ocenaRepository.findAllByStudent_IndexStudenta(student.get().getIndexStudenta());
        theModel.addAttribute("listaOcen", ocena);

        return "oceny";
    }

}
