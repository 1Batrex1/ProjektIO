package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BudynekSalaId implements Serializable {

    @Column(name = "Budynekid_budynku")
    private long budynekidBudynku;

    @Column(name = "Salaid_sali")
    private long salaid_sali;

    @Override
    public String toString() {
        return "BudynekSalaId{" +
                "budynekidBudynku=" + budynekidBudynku +
                ", salaid_sali=" + salaid_sali +
                '}';
    }
}
