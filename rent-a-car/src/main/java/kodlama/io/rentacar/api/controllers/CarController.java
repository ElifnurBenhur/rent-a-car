package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.buisness.abstracts.CarService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CarService service;
    @GetMapping("/filter/{pref}")
    List<GetAllCarsResponse> getAll(@PathVariable int pref){
        return  service.getAll(pref);
    }
    @GetMapping("/{id}") public GetCarResponse getById(@PathVariable int id){
        return  service.getById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request){
        return  service.add(request);
    }
    @PostMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
