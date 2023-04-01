package kodlama.io.rentacar.buisness.concretes;

import kodlama.io.rentacar.buisness.abstracts.ModelService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelResponse> response = models
                .stream()
                .map(model -> mapper.map(model, GetAllModelResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExist(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        checkIfModelExistByName(request.getName());
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);
        CreateModelResponse response = mapper.map(model, CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExist(id);
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);
        UpdateModelResponse response = mapper.map(model, UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExist(id);
        repository.deleteById(id);
    }

    private void checkIfModelExist(int id) {

        if (!repository.existsById(id)) throw new IllegalArgumentException("There is no such a model!");
    }

    private void checkIfModelExistByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Model already exist with this name!");
        }
    }
}
