package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class BudynekSalaId implements Serializable {

    private long budynekidBudynku;

    private long salaidSali;

    public BudynekSalaId() {

    }
}
