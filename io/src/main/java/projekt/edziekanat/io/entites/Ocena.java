package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ocena")
@NoArgsConstructor
@Data
public class Ocena {

    // Brakuje pola "wartosc oceny"

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_oceny")
    private int id;

    @Column(name = "waga")
    private int waga;

    @Column(name = "uwagi")
    private String uwaga;

    @Column(name = "typ")
    private String typ;

    @Column(name = "wartość")
    private int wartosc;

    @ManyToOne
    @JoinColumn(name ="id_studenta")
    Student student;

    @ManyToOne
    @JoinColumn(name ="id_przedmiotu")
    Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name ="id_wykladowcy")
    Wykladowca wykladowca;

    @Override
    public String toString() {
        return "Ocena{" +
                "id=" + id +
                ", waga=" + waga +
                ", uwaga='" + uwaga + '\'' +
                ", typ='" + typ + '\'' +
                ", wartosc=" + wartosc +
                ", student=" + student +
                ", przedmiot=" + przedmiot +
                ", wykladowca=" + wykladowca +
                '}';
    }
}
