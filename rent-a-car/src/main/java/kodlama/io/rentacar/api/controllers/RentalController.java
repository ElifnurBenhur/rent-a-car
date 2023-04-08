package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.buisness.abstracts.RentalService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateRentalRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateRentalRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllRentalsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/rentals")
@AllArgsConstructor
public class RentalController {
    private final RentalService service;
    @GetMapping
    List<GetAllRentalsResponse> getAll(){
        return  service.getAll();
    }
    @GetMapping("/{id}") public GetRentalResponse getById(@PathVariable int id){
        return  service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request){
        return  service.add(request);
    }
    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
