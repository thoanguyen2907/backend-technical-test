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

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "socket")
    private String socket;

    @Column(name = "fan_size")
    private Double fanSize;

    @Column(name = "fan_speed")
    private Integer fanSpeed;

    @Column(name = "fan_noise_level")
    private Double fanNoiseLevel;

    @Column(name = "number_of_fans")
    private Integer numberOfFans;

    @Column(name = "price")
    private Double price;

}
