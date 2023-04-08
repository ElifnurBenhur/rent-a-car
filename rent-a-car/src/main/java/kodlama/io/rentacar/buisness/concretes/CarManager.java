package kodlama.io.rentacar.buisness.concretes;

import kodlama.io.rentacar.buisness.abstracts.CarService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        //0-show maintenance 1-not show maintenance
        List<Car> cars =filterCarsByMaintenanceState(includeMaintenance);;

            List<GetAllCarsResponse> responses = cars
                    .stream()
                    .map(car -> mapper.map(car, GetAllCarsResponse.class))
                    .toList();
            return responses;



    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExist(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {

        Car car = mapper.map(request, Car.class);
        car.setId(0);
        car.setState(State.AVAILABLE);
        repository.save(car);
        CreateCarResponse response = mapper.map(car, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExist(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void changeState(int id, State state) {
        Car car=repository.findById(id).orElseThrow();
        car.setState(state);
        repository.save(car);

    }

    @Override
    public void delete(int id) {
        checkIfCarExist(id);
        repository.deleteById(id);
    }

    private void checkIfCarExist(int id) {

        if (!repository.existsById(id)) throw new IllegalArgumentException("There is no such a car!");
    }


    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) {
            return repository.findAll();
        }

        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }
}
