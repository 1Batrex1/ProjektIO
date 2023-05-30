package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "wykładowca")
@NoArgsConstructor
@Data
public class Wykladowca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "index_wykladowcy")
    private int indexWykladowcy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_osoba")
    private Osoba osoba;


}
