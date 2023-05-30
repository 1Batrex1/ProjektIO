package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obecność")
@NoArgsConstructor
@Data

public class Obecnosc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_obecnosc")
    private int idObecnosc;


    @Column(name = "obecnosc")
    private boolean obecnosc;

    @ManyToOne
    @JoinColumn(name = "id_zajęc")
    Zajecia zajecia;

    @ManyToOne
    @JoinColumn(name = "id_studenta")
    Student student;

    @Override
    public String toString() {
        return "Obecnosc{" +
                "idObecnosc=" + idObecnosc +
                ", obecnosc=" + obecnosc +
                ", zajecia=" + zajecia +
                ", student=" + student +
                '}';
    }
}
