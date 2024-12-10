package shopify.demo.model.base;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@Setter
public abstract class IdBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "id", updatable = false, nullable = false)
    private Long id;
}
