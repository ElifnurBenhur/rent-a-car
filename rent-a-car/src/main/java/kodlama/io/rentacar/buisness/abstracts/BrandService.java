package kodlama.io.rentacar.buisness.abstracts;

import kodlama.io.rentacar.buisness.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
//CRUD Operations
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(int id, UpdateBrandRequest request);
    void delete(int id);

}
