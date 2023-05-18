package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BudynekSalaId implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_budynku")
    private Budynek budynek;

    @OneToOne
    @JoinColumn(name = "id_sali")
    private Sala sala;


}
