package shopify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopify.demo.model.entity.ProductEntity;
import shopify.demo.model.entity.SocketEntity;

import java.util.UUID;

@Repository
public interface SocketRepository extends JpaRepository<SocketEntity, UUID> {

}
