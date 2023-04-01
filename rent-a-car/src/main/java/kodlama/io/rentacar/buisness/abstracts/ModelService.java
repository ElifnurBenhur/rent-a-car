package kodlama.io.rentacar.buisness.abstracts;

import kodlama.io.rentacar.buisness.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Model;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(int id, UpdateModelRequest request);
    void delete(int id);
}
