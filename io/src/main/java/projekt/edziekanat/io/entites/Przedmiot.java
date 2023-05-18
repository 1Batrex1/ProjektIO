package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "przedmiot")
@NoArgsConstructor
@Data
public class Przedmiot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_przedmiotu")
    private int idPrzedmiotu;

    @Column(name = "ects")
    private int ects;

    @Column(name = "nazwa_przedmiotu")
    private String nazwaPrzedmiotu;

}
