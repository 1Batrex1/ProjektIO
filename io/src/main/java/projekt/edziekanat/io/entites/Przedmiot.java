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

    @Column(name = "typzajęć")
    private String typZajec;

    @Column(name = "semestr")
    private int semestr;

    @Override
    public String toString() {
        return "Przedmiot{" +
                "idPrzedmiotu=" + idPrzedmiotu +
                ", ects=" + ects +
                ", nazwaPrzedmiotu='" + nazwaPrzedmiotu + '\'' +
                ", typZajec='" + typZajec + '\'' +
                ", semestr=" + semestr +
                '}';
    }
}
