package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.buisness.abstracts.MaintenanceService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@AllArgsConstructor
public class MaintenanceController {
    private final MaintenanceService service;
    @GetMapping
    List<GetAllMaintenancesResponse> getAll(){
        return  service.getAll();
    }
    @GetMapping("/{id}") public GetMaintenanceResponse getById(@PathVariable int id){
        return  service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request){
        return  service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
