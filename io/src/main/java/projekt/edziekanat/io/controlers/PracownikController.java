package projekt.edziekanat.io.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.edziekanat.io.dao.BudynekSalaRepository;
import projekt.edziekanat.io.dao.GrupaRepository;
import projekt.edziekanat.io.dao.PrzedmiotRepository;
import projekt.edziekanat.io.dao.WykladowcaRepository;
import projekt.edziekanat.io.entites.*;

import java.util.List;

@Controller
public class PracownikController {


    WykladowcaRepository wykladowcaRepository;

    GrupaRepository grupaRepository;

    // To trzeba naprawić więc na razie tego nie dodaje
    BudynekSalaRepository budynekSalaRepository;

    PrzedmiotRepository przedmiotRepository;
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
    public String zapiszPlan(@RequestParam String wykladowca, @RequestParam String grupa, @RequestParam String przedmiot, @RequestParam String data,@RequestParam String budynekSala)
    {
        System.out.println("Selected Wykladowca: " + wykladowca);
        System.out.println("Selected Grupa: " + grupa);
        System.out.println("Selected Przedmiot: " + przedmiot);
        System.out.println("Selected Data: " + data);
        System.out.println("Selected BudynekSala: " + budynekSala);


        return "redirect:/";
    }
    @Autowired
    public PracownikController(PrzedmiotRepository przedmiotRepository,WykladowcaRepository wykladowcaRepository, GrupaRepository grupaRepository, BudynekSalaRepository budynekSalaRepository) {
        this.wykladowcaRepository = wykladowcaRepository;
        this.grupaRepository = grupaRepository;
        this.budynekSalaRepository = budynekSalaRepository;
        this.przedmiotRepository = przedmiotRepository;
    }
}
