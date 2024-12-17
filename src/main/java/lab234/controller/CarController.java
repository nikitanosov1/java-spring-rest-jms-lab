package lab234.controller;


import lab234.model.Car;
import lab234.model.Owner;
import lab234.service.CarService;
import lab234.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("car", new Car());
        List<UUID> ownerIds = ownerService.getAll().stream().map(Owner::getId).collect(Collectors.toList());
        model.addAttribute("ownerIds", ownerIds);
        return "CreateCar";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Car car, @RequestParam("ownerId") UUID ownerId) {
        carService.create(car, ownerId);
        return "redirect:/cars";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Car> cars = carService.getAll();
        model.addAttribute("cars", cars);
        return "ShowCars";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID carId, Model model) {
        model.addAttribute("car", carService.get(carId));
        List<UUID> ownerIds = ownerService.getAll().stream().map(Owner::getId).collect(Collectors.toList());
        model.addAttribute("ownerIds", ownerIds);
        return "UpdateCar";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") UUID carId, @ModelAttribute Car car, @RequestParam UUID ownerId) {
        carService.update(carId, car, ownerId);
        return "redirect:/cars";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        carService.delete(id);
        return "redirect:/cars";
    }

}
