package lab234.repo;

import lab234.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    List<Car> findAllByOwnerId(UUID owner);
    List<Car> deleteAllByOwnerId(UUID owner);
}
