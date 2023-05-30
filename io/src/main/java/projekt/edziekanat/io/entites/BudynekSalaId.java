package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BudynekSalaId implements Serializable {

    private long budynekidBudynku;

    private long salaid_sali;

}
