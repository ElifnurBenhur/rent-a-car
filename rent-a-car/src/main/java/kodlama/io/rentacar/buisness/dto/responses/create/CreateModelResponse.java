package kodlama.io.rentacar.buisness.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelResponse {
    private int id;
    private int brandId;
    private String name;
}
