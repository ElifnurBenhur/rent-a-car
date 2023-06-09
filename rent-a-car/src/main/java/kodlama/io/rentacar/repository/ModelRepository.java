package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    boolean existsByNameIgnoreCase(String name);
}
