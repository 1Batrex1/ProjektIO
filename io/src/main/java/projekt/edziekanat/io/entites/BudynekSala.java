package projekt.edziekanat.io.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table(name = "budynek_sala")
@Entity
@Data
@NoArgsConstructor
public class BudynekSala {

    @EmbeddedId
    private BudynekSalaId id;
}
