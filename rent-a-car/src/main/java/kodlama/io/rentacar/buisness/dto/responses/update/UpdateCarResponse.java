package kodlama.io.rentacar.buisness.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private int state;//1-Available 2-Rented 3-Maintenance
    private double dailyPrice;
    private int modelId;
}
