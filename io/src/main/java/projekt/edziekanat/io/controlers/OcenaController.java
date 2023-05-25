package projekt.edziekanat.io.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekt.edziekanat.io.dao.OcenaRepository;
import projekt.edziekanat.io.dao.StudentRepository;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

@Controller
public class OcenaController {

    StudentRepository studentRepository;

    OcenaRepository ocenaRepository;
    @Autowired
    OcenaController(OcenaRepository ocenaRepository)
    {this.ocenaRepository = ocenaRepository;}

    @GetMapping("/sprawdz-oceny/{studentId}")
    public String wyswietlOceny(Model theModel, @PathVariable int studentId) {

        List<Ocena> ocena = ocenaRepository.findAllByStudent_IndexStudenta(studentId);
        theModel.addAttribute("listaOcen", ocena);

        return "oceny";
    }

}
