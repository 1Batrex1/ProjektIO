package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sali")
    private int idSali;

}
