package projekt.edziekanat.io.entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupa")
@NoArgsConstructor
@Data
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_grupy")
    private int idGrupy;

    @ManyToOne
    @JoinColumn(name = "id_wykladowcy")
    private Wykladowca wykladowca;
}