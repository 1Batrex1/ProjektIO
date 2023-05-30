package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    @Column(name = "rok_akademicki")
    private int rok;

    @Column(name = "specjalnosc")
    private String specjalnosc;

    @Column(name = "rok_rozpoczecia")
    private int rokRozpoczecia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_osoba")
    private Osoba osoba;

    @OneToMany(mappedBy = "student")
    List<Ocena> ocena;

    @ManyToOne
    @JoinColumn(name = "id_grupy")
    Grupa grupa;

    @Override
    public String toString() {
        return "Student{" +
                "indexStudenta=" + indexStudenta +
                ", semestr=" + semestr +
                ", rok=" + rok +
                ", specjalnosc='" + specjalnosc + '\'' +
                ", rokRozpoczecia=" + rokRozpoczecia +
                ", osoba=" + osoba +
                ", ocena=" + ocena +
                ", grupa=" + grupa +
                '}';
    }
}
