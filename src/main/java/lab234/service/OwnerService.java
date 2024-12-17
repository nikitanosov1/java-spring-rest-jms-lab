package lab234.service;


import lab234.aspect.JmsLoggable;
import lab234.model.Car;
import lab234.model.Owner;
import lab234.repo.CarRepository;
import lab234.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarRepository carRepository;

    @JmsLoggable
    public void create(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner get(UUID id) {
        return ownerRepository.findById(id).get();
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll().stream().sorted(Comparator.comparing(Owner::getId)).collect(Collectors.toList());
    }
    @JmsLoggable
    public void update(UUID ownerId, Owner newOwnerData) {
        Owner owner = ownerRepository.findById(ownerId).get();
        owner.setFirstName(newOwnerData.getFirstName());
        owner.setLastName(newOwnerData.getLastName());
        owner.setBirthdate(newOwnerData.getBirthdate());
        ownerRepository.save(owner);
    }
    @JmsLoggable
    public void delete(UUID playerId) {
        carRepository.deleteAllByOwnerId(playerId);
        ownerRepository.deleteById(playerId);
    }

    public List<Car> getHeroes(UUID id) {
        Owner owner = get(id);
        return owner.getCars().stream().sorted(Comparator.comparing(Car::getModelName)).collect(Collectors.toList());
    }
}

