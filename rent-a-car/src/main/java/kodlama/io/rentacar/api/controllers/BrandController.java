package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.buisness.abstracts.BrandService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {
private final BrandService service;
@GetMapping
    List<GetAllBrandsResponse> getAll(){
    return  service.getAll();
    }
    @GetMapping("/{id}") public GetBrandResponse getById(@PathVariable int id){
        return  service.getById(id);
    }
@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public CreateBrandResponse add(@RequestBody CreateBrandRequest request){
    return  service.add(request);
}
@PutMapping("/{id}")
 public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest brand){
    return service.update(id,brand);
}
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
    service.delete(id);
}
}
