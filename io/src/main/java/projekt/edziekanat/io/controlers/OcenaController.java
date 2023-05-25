package projekt.edziekanat.io.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekt.edziekanat.io.entites.Ocena;
import projekt.edziekanat.io.service.OcenaService;

import java.util.List;

@Controller
public class OcenaController {

    private OcenaService ocenaService;

    @Autowired
    public OcenaController(OcenaService ocenaService) {
        this.ocenaService = ocenaService;
    }

    @GetMapping("/sprawdz-oceny/{studentId}")
    public String wyswietlOceny(Model theModel, @PathVariable int studentId) {
        List<Ocena> oceny = ocenaService.findAllByStudentId(studentId);

        theModel.addAttribute("listaOcen", oceny);

        System.out.println(oceny);

        return "oceny";
    }

}
