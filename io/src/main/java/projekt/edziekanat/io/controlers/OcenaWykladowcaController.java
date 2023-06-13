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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Wykladowca> wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(Integer.parseInt(authentication.getName()));
        int idWykladowcy = wykladowca.get().getIndexWykladowcy();
        List<Zajecia> zajeciaList = zajeciaRepository.findDistinctByWykladowca_IndexWykladowcy(idWykladowcy);

        List<Zajecia> zajeciaListTmp = new ArrayList<>();

        for (int i = 0; i < zajeciaList.size(); i++) {
            for (int j = i + 1; j < zajeciaList.size(); j++) {
                if(zajeciaList.get(i).getGrupa() == zajeciaList.get(j).getGrupa()
                    && zajeciaList.get(i).getPrzedmiot().getNazwaPrzedmiotu().equals(zajeciaList.get(j).getPrzedmiot().getNazwaPrzedmiotu())
                    && zajeciaList.get(i).getPrzedmiot().getTypZajec().equals(zajeciaList.get(j).getPrzedmiot().getTypZajec())) {
                    zajeciaList.remove(j);
                    i = 0;
                }
            }
        }

        for (int i = 0; i < zajeciaList.size(); i++) {
            for (int j = i + 1; j < zajeciaList.size(); j++) {
                if(zajeciaList.get(i).getGrupa() == zajeciaList.get(j).getGrupa()
                        && zajeciaList.get(i).getPrzedmiot().getNazwaPrzedmiotu().equals(zajeciaList.get(j).getPrzedmiot().getNazwaPrzedmiotu())
                        && zajeciaList.get(i).getPrzedmiot().getTypZajec().equals(zajeciaList.get(j).getPrzedmiot().getTypZajec())) {
                    zajeciaList.remove(j);
                    i = 0;
                }
            }
        }

        /*for (Zajecia zajecia1: zajeciaList) {
            for (Zajecia zajecia2: zajeciaListTmp) {
                if (zajecia1.getPrzedmiot().getNazwaPrzedmiotu().equals(zajecia2.getPrzedmiot().getNazwaPrzedmiotu())
                    && zajecia1.getPrzedmiot().getTypZajec().equals(zajecia2.getPrzedmiot().getTypZajec())
                    && zajeciaList.size() > 1) {
                    zajeciaList.remove(zajecia1);
                }
            }
        }*/

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
        int idOsoby = Integer.parseInt(authentication.getName());

        ocena.setStudent(studentRepository.findByIndexStudenta(indexStudenta).get());
        ocena.setPrzedmiot(przedmiotRepository.findByIdPrzedmiotu(idPrzedmiotu).get());
        ocena.setWykladowca(wykladowcaRepository.findWykladowcaByOsobaId(idOsoby).get());

        if (ocena.isTyp()) {
            Optional<Ocena> tempOcena = ocenaRepository.findByStudent_IndexStudentaAndPrzedmiot_IdPrzedmiotuAndTyp(indexStudenta, idPrzedmiotu, true);
            if (tempOcena.isPresent()) {

                return "redirect:/formularzOceny?indexStudenta=" + indexStudenta;
            }
        }

        if (ocena.getWartosc() == 0) {
            return "redirect:/formularzOceny?indexStudenta=" + indexStudenta;
        }

        ocenaRepository.save(ocena);

        return "redirect:/wybierzZajecia";
    }
    @GetMapping("/grafik")
    public String wyswietlGrafik(Model theModel)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int idOsoby = Integer.parseInt(authentication.getName());

        Optional<Wykladowca> wykladowca = wykladowcaRepository.findWykladowcaByOsobaId(idOsoby);
        List<Zajecia> zajeciaList = zajeciaRepository.findAllByWykladowca_IndexWykladowcyOrderByDataZajec(wykladowca.get().getIndexWykladowcy());

        theModel.addAttribute("zajecia",zajeciaList);

        return "grafik";
    }
}
