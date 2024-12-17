package lab234.rest;

import lab234.model.Car;
import lab234.repo.CarRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/rest")
public class RestService {

    private final CarRepository carRepository;

    @Autowired
    public RestService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/json/{id}")
    public ResponseEntity<List<Car>> getJsonCars(@PathVariable(name = "id") String Id) {
        UUID formattedId = UUID.fromString(Id);

        List<Car> cars = carRepository.findAllByOwnerId(formattedId);

        return cars != null
                ? new ResponseEntity<>(cars, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXMLCars(@PathVariable(name = "id") String id) throws JsonProcessingException {
        UUID formattedId = UUID.fromString(id);
        List<Car> cars = carRepository.findAllByOwnerId(formattedId);

        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(cars);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml);
    }
}
