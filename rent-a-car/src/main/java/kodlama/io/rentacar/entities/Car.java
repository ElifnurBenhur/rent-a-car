package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    //TODO ENUM
    //private int state;//1-Available 2-Rented 3-Maintenance
    @Enumerated(EnumType.STRING)
    private State state;
    private double dailyPrice;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToMany(mappedBy = "car")//modelde car id foreign key olarak tutulmayacak!
    private List<Maintenance> maintenances;


    @OneToMany(mappedBy = "car")//modelde car id foreign key olarak tutulmayacak!
    private List<Rental> rentals;
}
