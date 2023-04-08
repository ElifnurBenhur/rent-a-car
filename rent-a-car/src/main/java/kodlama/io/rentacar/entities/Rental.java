package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rentals")
public class Rental {
   // id, carId, dailyPrice, rentedForDays, totalPrice, startDate.
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private double dailyPrice;
   private int rentedForDays;

   private double totalPrice;
   private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
