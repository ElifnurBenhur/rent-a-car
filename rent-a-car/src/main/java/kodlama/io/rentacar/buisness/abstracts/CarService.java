package kodlama.io.rentacar.buisness.abstracts;

import kodlama.io.rentacar.buisness.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);

    void changeState(int id, State state);
    void delete(int id);
}
