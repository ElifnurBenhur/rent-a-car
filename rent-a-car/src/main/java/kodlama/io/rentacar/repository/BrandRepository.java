package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import  java.util.List;
//<> generic yapıdır
//jpa interface için primitive tip kullanamayız
//artık @repository yazmama gerek yok
public interface BrandRepository extends JpaRepository<Brand,Integer> {
  //Methodlara gerek kalmadı
    //Custom queries yazılabilir sadece
    boolean existsByNameIgnoreCase(String name);
}
