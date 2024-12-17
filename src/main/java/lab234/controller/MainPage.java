package lab234.controller;

import lab234.model.Car;
import lab234.model.Owner;
import lab234.repo.CarRepository;
import lab234.repo.OwnerRepository;
import lab234.service.CarService;
import lab234.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPage {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;
    @GetMapping("/mainPage")
    public String mainPage(Model model1, Model model2){
        List<Owner> owners = ownerRepository.findAll();
        List<Car> cars = carRepository.findAll();
        model1.addAttribute("owners", owners);
        model2.addAttribute("cars", cars);
        return "MainPage";
    }
}
