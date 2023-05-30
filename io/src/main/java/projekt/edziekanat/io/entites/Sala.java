package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sali")
    private int idSali;

    @Override
    public String toString() {
        return "Sala{" +
                "idSali=" + idSali +
                '}';
    }
}

