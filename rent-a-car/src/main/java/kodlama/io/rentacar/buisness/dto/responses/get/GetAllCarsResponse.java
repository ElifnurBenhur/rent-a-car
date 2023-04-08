package kodlama.io.rentacar.buisness.dto.responses.get;

import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCarsResponse {
    private int id;
    private int modelYear;
    private String plate;
    private State state;//1-Available 2-Rented 3-Maintenance
    private double dailyPrice;
    private int modelId;

}
