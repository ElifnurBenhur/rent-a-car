package kodlama.io.rentacar.buisness.concretes;

import kodlama.io.rentacar.buisness.abstracts.BrandService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//servis olduğunu belirtmek için
@Service
//lombok ile const oluşturabiliriz
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands= repository.findAll();
        List<GetAllBrandsResponse> response=brands
                .stream()
                .map(brand -> mapper.map(brand,GetAllBrandsResponse.class))
                .toList();
        //new ArrayList<>();
        /*
        for (Brand brand:brands
             ) {
            response.add(new GetAllBrandsResponse(brand.getId(), brand.getName()));
        }*/ //bu foreach yerine stream api kullanarak daha okunaklı ve pratik kod yazabiliriz yukarıda örneği
        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExist(id);
        Brand brand=repository.findById(id).orElseThrow();
        GetBrandResponse response=mapper.map(brand,GetBrandResponse.class);
        //tek tek getter setter kullanmak yerine mapper ile kodu sadeleştiriyorum.
        /*new GetBrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());*/
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        //aynı isimle marka varsa yeni kayıt oluşturmaz
        checkIfBrandExistByName(request.getName());
        Brand brand=mapper.map(request,Brand.class);
        /*brand.setName(request.getName());
        //id otomatik oluşturuluyor!!!
        CreateBrandResponse response=new CreateBrandResponse();
        response.setName(brand.getName());
        response.setId(brand.getId());*/
        brand.setId(0);
        //içinde başka bir id verisi varsa mapper onun oluşturulan nesneye
        // id olarak atanmasına sebep olur bunu çözmek için 0 veririz
        // ve bu id var olmadığından yeni bir id ile yeni bir nesne oluşturmak zorunda kalır.
        repository.save(brand);
        return mapper.map(brand,CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        //?
        checkIfBrandExist(id);
        Brand brand=mapper.map(request,Brand.class);
        brand.setId(id);
        return mapper.map(brand,UpdateBrandResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfBrandExist(id);
       repository.deleteById(id);
    }

    //iş kuralları
    private void checkIfBrandExist(int id){

        if(!repository.existsById(id)) throw new IllegalArgumentException("There is no such a brand!");
    }

    private void checkIfBrandExistByName(String name){
        if (repository.existsByNameIgnoreCase(name)){
            throw new RuntimeException("Brand already exist with this name!");
        }
    }
}
