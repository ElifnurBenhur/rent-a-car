package kodlama.io.rentacar.buisness.concretes;

import kodlama.io.rentacar.buisness.abstracts.CarService;
import kodlama.io.rentacar.buisness.abstracts.MaintenanceService;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.*;
import kodlama.io.rentacar.buisness.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.buisness.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.buisness.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> responses = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
        return responses;

    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance=repository.findByCarIdAndIsCompletedIsFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        //GetCarResponse carResponse=carService.getById(request.getCarId());
        //checkIfCarAvailable(carResponse.getState());
       /* if(repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())){
            throw new RuntimeException("Car is already in maintenance");
        }*/
        checkIfCarUnderMaintenance(request);
        checkCarAvailabilityForMaintenance(request);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        carService.changeState(request.getCarId(),State.MAINTENANCE);
        //carService.update(carResponse.getId(),mapper.map(carResponse, UpdateCarRequest.class));//db savelemek için
        repository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);
      //  response.setCarState(3);
        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        carService.changeState(repository.findById(id).get().getCar().getId(), State.AVAILABLE);
        repository.deleteById(id);
    }

    private void checkIfMaintenanceExist(int id) {

        if (!repository.existsById(id)) throw new IllegalArgumentException("There is no such a maintenance!");
    }

    private void checkIfCarAvailable(int state){
        if(state!=1) throw new IllegalArgumentException("The car is not available!");
    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }

    private void checkIfCarUnderMaintenance(CreateMaintenanceRequest request) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())) {
            throw new RuntimeException("Araç şuanda bakımda!");
        }
    }

    private void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
       if(carService.getById(request.getCarId()).getState().equals(State.RENTED)) {
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }





}
