package kodlama.io.rentacar.buisness.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateRentalRequest {

    private int carId;
    private double dailyPrice;
    private int rentedForDays;


    private LocalDateTime startDate;
}
