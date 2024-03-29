package projekt.edziekanat.io.entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "zajęcia")
@NoArgsConstructor
@Data
public class Zajecia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zajęc")
    private int idZajec;

    @Column(name = "data")
    Date dataZajec;

    @Column(name = "godzina_zajęć")
    Time godzinaZajec;

    @ManyToOne
    @JoinColumn(name ="id_przedmiotu")
    Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name ="id_wykladowcy")
    Wykladowca wykladowca;

    @ManyToOne
    @JoinColumn(name = "id_grupy")
    Grupa grupa;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_budynku",referencedColumnName = "budynekidBudynku"),
            @JoinColumn(name = "id_sali",referencedColumnName = "salaidSali")
    })
    BudynekSala budynekSala;



}
