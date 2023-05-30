package projekt.edziekanat.io.controlers;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.edziekanat.io.dao.*;
import projekt.edziekanat.io.entites.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PracownikController {


    WykladowcaRepository wykladowcaRepository;

    GrupaRepository grupaRepository;

    // To trzeba naprawić więc na razie tego nie dodaje
    BudynekSalaRepository budynekSalaRepository;

    PrzedmiotRepository przedmiotRepository;

    ZajeciaRepository zajeciaRepository;

    EntityManager entityManager;
    @GetMapping("/ustalaniePlanu")
    public String ustalPlan(Model theModel)
    {
        List<Wykladowca> wykladowcaList= wykladowcaRepository.findAll();
        List<Grupa> grupaList = grupaRepository.findAll();
        List<Przedmiot> przedmiotList = przedmiotRepository.findAll();
        List<BudynekSala> budynekSalaList = budynekSalaRepository.findAll();
        theModel.addAttribute("Grupy",grupaList);
        theModel.addAttribute("Wykladowcy",wykladowcaList);
        theModel.addAttribute("Przedmioty",przedmiotList);
        theModel.addAttribute("BudynekSala",budynekSalaList);
        return "ustalanie-planu";
    }
    // Tutaj trzeba dodać ify i zapisać
    @PostMapping("/ustalanie-planu/save")
    public String zapiszPlan(@RequestParam String wykladowca, @RequestParam String grupa, @RequestParam String przedmiot, @RequestParam String data,@RequestParam String budynekSala) throws ParseException {

        //Konwertowanie daty
        String[] strings = data.split("T");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(strings[0]);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time time =new Time(timeFormat.parse(strings[1]).getTime());

        //Konwertowanie id sali i budynku

        strings = budynekSala.split(" ");


        Optional<Zajecia> zajecia1 = zajeciaRepository.findByWykladowca_IndexWykladowcyAndDataZajecEqualsAndGodzinaZajecEquals(Integer.valueOf(wykladowca),date,time);
        Optional<Zajecia> zajecia2 = zajeciaRepository.findByBudynekSala_Id_BudynekidBudynkuAndBudynekSala_Id_SalaidSaliAndDataZajecEqualsAndGodzinaZajecEquals(Integer.valueOf(strings[0]),Integer.valueOf(strings[1]),date,time);
        Optional<Zajecia> zajecia3 = zajeciaRepository.findByGrupa_IdGrupyAndDataZajecEqualsAndGodzinaZajecEquals(Integer.valueOf(grupa),date,time);
        if(zajecia1.isPresent() || zajecia2.isPresent() || zajecia3.isPresent())
        {
            System.out.println("Znalazłem");
            return "redirect:/";

        }

        Optional<Wykladowca> wykladowca1 = wykladowcaRepository.findById(Integer.valueOf(wykladowca));
        Optional<BudynekSala> budynekSala1 = budynekSalaRepository.findById(new BudynekSalaId((Integer.parseInt(strings[0])),Integer.parseInt(strings[1])));
        Optional<Grupa> grupa1 = grupaRepository.findById(Integer.valueOf(grupa));
        Optional<Przedmiot> przedmiot1 = przedmiotRepository.findById(Integer.valueOf(przedmiot));
        Zajecia tempZajecia = new Zajecia();

        tempZajecia.setWykladowca(wykladowca1.get());
        tempZajecia.setPrzedmiot(przedmiot1.get());
        tempZajecia.setBudynekSala(budynekSala1.get());
        tempZajecia.setGrupa(grupa1.get());
        tempZajecia.setGodzinaZajec(time);
        tempZajecia.setDataZajec(date);
        zajeciaRepository.save(tempZajecia);
        return "redirect:/";
    }
    @Autowired
    public PracownikController(EntityManager entityManager,ZajeciaRepository zajeciaRepository,PrzedmiotRepository przedmiotRepository,WykladowcaRepository wykladowcaRepository, GrupaRepository grupaRepository, BudynekSalaRepository budynekSalaRepository) {
        this.entityManager = entityManager;
        this.wykladowcaRepository = wykladowcaRepository;
        this.grupaRepository = grupaRepository;
        this.budynekSalaRepository = budynekSalaRepository;
        this.przedmiotRepository = przedmiotRepository;
        this.zajeciaRepository = zajeciaRepository;
    }
}
