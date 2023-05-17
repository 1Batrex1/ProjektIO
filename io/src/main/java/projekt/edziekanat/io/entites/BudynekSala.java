package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class BudynekSala {

    @Id
    @OneToOne
    @JoinColumn(name = "id_budynku")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Budynek idBudynku;


    @Id
    @OneToOne
    @JoinColumn(name = "id_sali")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Sala idSali;
}
