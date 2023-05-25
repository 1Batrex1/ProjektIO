package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ocena")
@NoArgsConstructor
@Data
public class Ocena {

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

    @ManyToOne
    @JoinColumn(name ="id_studenta")
    Student student;

    @ManyToOne
    @JoinColumn(name ="id_przedmiotu")
    Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name ="id_wykladowcy")
    Wykladowca wykladowca;

}
