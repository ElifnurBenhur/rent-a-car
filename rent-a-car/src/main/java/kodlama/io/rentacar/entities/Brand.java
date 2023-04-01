package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//jpa jakarta SQL nesnesi olduğunu belirtmek için
@Entity
//veri tabanında tablo oluşturmak için
@Table(name = "brands")
//code first yani kod ile belirttiğim tablolar db de oluşur
public class Brand {
    //pk Oldugunu belirtmek için
    @Id
    //db de column oluşturmak için
    //farklı bir isimle de belirtebiliriz
    //aynı isim kullanacaksak column gerek yok
   // @Column(name = "id")
    //id sıralamasını otomatik yapması için
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "brand") //Bir markanın birden çok modeli olabilir
    // mappedBy brand brand de model foreign key tutulmasını önler
    //tek sorgu ile brand'in markalarına ulaşabilmek için ve ilişkinin iki yönünü de belirtmek için
   private List<Model> models;



}
