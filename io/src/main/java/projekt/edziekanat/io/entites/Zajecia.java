package projekt.edziekanat.io.entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "zajęcia")
@NoArgsConstructor
@Data
public class Zajecia {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zajęc")
    private int idZajec;

    @Column(name = "data")
    Date dataZajec;

    @ManyToOne
    @JoinColumn(name ="id_przedmiotu")
    Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name ="id_wykladowcy")
    Wykladowca wykladowca;

    @ManyToOne
    @JoinColumn(name = "id_grupy")
    Grupa grupa;

    //Dodać id sali

   /* @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "budynekidBudynku",referencedColumnName = "Budynekid_budynku"),
            @JoinColumn(name = "salaid_sali", referencedColumnName = "Salaid_sali")
    })
    BudynekSala budynekSala;*/
    //Dodać id budynku


    @Override
    public String toString() {
        return "Zajecia{" +
                "idZajec=" + idZajec +
                ", dataZajec=" + dataZajec +
                ", przedmiot=" + przedmiot +
                ", wykladowca=" + wykladowca +
                ", grupa=" + grupa +
                '}';
    }
}
