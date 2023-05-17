package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "index_studenta")
    private int indexStudenta;

    @Column(name = "semestr")
    private int semestr;

    @Column(name = "rok")
    private int rok;

    @Column(name = "specjalnosc")
    private String specjalnosc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_osoba")
    private Osoba osoba;

}
