package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.buisness.abstracts.ModelService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
@AllArgsConstructor
public class ModelController {
    private final ModelService service;
    @GetMapping
    List<GetAllModelResponse> getAll(){
        return  service.getAll();
    }
    @GetMapping("/{id}") public GetModelResponse getById(@PathVariable int id){
        return  service.getById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request){
        return  service.add(request);
    }
    @PostMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
