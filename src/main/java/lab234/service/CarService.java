package lab234.service;

import lab234.aspect.JmsLoggable;
import lab234.model.Car;
import lab234.model.Owner;
import lab234.repo.CarRepository;
import lab234.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @JmsLoggable
    public void create(Car car, UUID playerId) {
        Owner owner = ownerRepository.findById(playerId).get();
        car.setOwner(owner);
        carRepository.save(car);
    }

    public Car get(UUID id) {
        return carRepository.findById(id).get();
    }

    public List<Car> getAll() {
        return carRepository.findAll().stream().sorted(Comparator.comparing(Car::getModelName)).collect(Collectors.toList());
    }

    @JmsLoggable
    public void update(UUID heroId, Car newCarData, UUID ownerId) {
        Car car = carRepository.findById(heroId).get();
        car.setBrand(newCarData.getBrand());
        car.setModelName(newCarData.getModelName());
        Owner owner = ownerRepository.findById(ownerId).get();
        car.setOwner(owner);
        carRepository.save(car);
    }

    public void delete(UUID id) {
        Car car = carRepository.findById(id).get();
        car.getOwner().getCars().remove(car);
        carRepository.deleteById(id);
    }
}
