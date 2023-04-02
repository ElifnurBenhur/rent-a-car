package kodlama.io.rentacar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	/*
	@Bean //method olduğu için
	//bu method uygulama başladığında otmatik mapper oluşmasını sağlar ve her sınıfta oluşturup bellek tüketmemizi önler!
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
    */ // bu kısmı config e taşımak paketlemeye daha uygun!
}
//Notlar*****
//Lombok run time da olduğu için bazen tercih edilmeyebilir
//Circular mapping önlemek için dto kullanırız
