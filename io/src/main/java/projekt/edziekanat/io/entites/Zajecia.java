package projekt.edziekanat.io.entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "zajęcia")
@NoArgsConstructor
@Data
public class Zajecia {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zajęc")
    private int idZajec;

    @Column(name = "data")
    Date dataZajec;

    @ManyToOne
    @JoinColumn(name ="id_przedmiotu")
    Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name ="id_wykladowcy")
    Wykladowca wykladowca;

    @ManyToOne
    @JoinColumn(name = "id_grupa")
    Grupa grupa;

    //Dodać id sali
    //Dodać id budynku



}