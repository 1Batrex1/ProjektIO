package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Osoba")
@Data
@NoArgsConstructor
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_osoba")
    private int id;

    @Column(name = "imię")
    private String imie;


    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "pesel")
    private String pesel;


}
