package shopify.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import shopify.demo.model.base.BaseEntity;

import java.util.UUID;

@Entity
@Table(name = "sockets")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocketEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
}
