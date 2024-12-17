package lab234.repo;

import lab234.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OwnerRepository extends JpaRepository<Owner, UUID> {

}
