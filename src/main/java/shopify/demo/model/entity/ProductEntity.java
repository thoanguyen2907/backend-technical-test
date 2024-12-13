package shopify.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import shopify.demo.model.base.BaseEntity;

import java.util.UUID;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "socket", nullable = false)
    private String socket;

    @Column(name = "fan_size" , nullable = false)
    private Double fanSize;

    @Column(name = "fan_speed", nullable = false)
    private Integer fanSpeed;

    @Column(name = "fan_noise_level", nullable = false)
    private Double fanNoiseLevel;

    @Column(name = "number_of_fans", nullable = false)
    private Integer numberOfFans;

    @Column(name = "price", nullable = false)
    private Double price;

}
