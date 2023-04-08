package kodlama.io.rentacar.buisness.abstracts;

import kodlama.io.rentacar.buisness.dto.requests.create.CreateRentalRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateRentalRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllRentalsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}
