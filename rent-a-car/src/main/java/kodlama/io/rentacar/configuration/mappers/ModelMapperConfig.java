package kodlama.io.rentacar.configuration.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean //method olduğu için
    //bu method uygulama başladığında otmatik mapper oluşmasını sağlar ve her sınıfta oluşturup bellek tüketmemizi önler!
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
