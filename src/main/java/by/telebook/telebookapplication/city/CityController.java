package by.telebook.telebookapplication.city;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/city")
public class CityController {
    private CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostMapping
    public ResponseEntity<City> create(@Valid @RequestBody City city) {
        City city1 = cityRepository.save(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteId/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        Optional<City> city1 = cityRepository.findById(id);
        if (city1.isPresent()) {
            cityRepository.deleteById(id);
            return new ResponseEntity<>(cityRepository.findById(id).get().getName() + " Deleted"
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>("City with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/city/{id}")
    public ResponseEntity<City> updateCityNameById(@RequestParam("name") String city, @PathVariable Long id) {
        Optional<City> city1 = cityRepository.findById(id);
        if (city1.isPresent()) {
            city1.get().setName(city);
            return new ResponseEntity<>(city1.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/update/country/{id}")
    public ResponseEntity<City> updateCityCountryById(@RequestParam String country, @PathVariable Long id) {
        Optional<City> city1 = cityRepository.findById(id);
        if (city1.isPresent()) {
            city1.get().setCountry(country);
            return new ResponseEntity<>(cityRepository.save(city1.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
