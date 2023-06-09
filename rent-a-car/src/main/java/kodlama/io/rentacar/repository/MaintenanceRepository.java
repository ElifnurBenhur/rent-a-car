package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
    boolean existsByCarIdAndIsCompletedIsFalse(int carId);

    Maintenance findByCarIdAndIsCompletedIsFalse(int carId);

}
