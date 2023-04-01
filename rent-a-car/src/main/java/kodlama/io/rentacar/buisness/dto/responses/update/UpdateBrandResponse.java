package kodlama.io.rentacar.buisness.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//record kullansaydık içinde otomatik const ve bazı metodlar tanımlı!
// ama getter setter manuel çevirme gerektiriyor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandResponse {

    private String name;
}
