package projekt.edziekanat.io.entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "grupa")
@NoArgsConstructor
@Data
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_grupy")
    private int idGrupy;


    @OneToMany(mappedBy = "grupa")
    List<Student> studentList;



}