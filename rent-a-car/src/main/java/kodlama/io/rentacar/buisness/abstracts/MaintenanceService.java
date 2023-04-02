package kodlama.io.rentacar.buisness.abstracts;

import kodlama.io.rentacar.buisness.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int id);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    void delete(int id);
}
