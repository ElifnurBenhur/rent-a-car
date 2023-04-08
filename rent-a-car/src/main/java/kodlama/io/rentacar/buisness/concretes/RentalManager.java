package kodlama.io.rentacar.buisness.concretes;

import kodlama.io.rentacar.buisness.abstracts.CarService;
import kodlama.io.rentacar.buisness.abstracts.RentalService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateRentalRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateRentalRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllRentalsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetRentalResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateRentalResponse;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor

@Service
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;

    private final CarService carService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExist(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {

        Rental rental = mapper.map(request, Rental.class);
        checkIfCarAvailable(rental.getCar().getId());
        rental.setId(0);
        rental.setTotalPrice(rental.getDailyPrice()* rental.getRentedForDays());
        repository.save(rental);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);
        carService.changeState(request.getCarId(), State.RENTED);
        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        checkIfRentalExist(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfRentalExist(id);
        carService.changeState(repository.findById(id).get().getCar().getId(), State.AVAILABLE);
        repository.deleteById(id);
    }

    private void checkIfRentalExist(int id) {

        if (!repository.existsById(id)) throw new IllegalArgumentException("There is no such a Rental!");
    }

    private void checkIfCarAvailable(int carId){

       if(!carService.getById(carId).getState().equals(State.AVAILABLE)){
           throw new RuntimeException("The car is not available!");
       }

    }

}
