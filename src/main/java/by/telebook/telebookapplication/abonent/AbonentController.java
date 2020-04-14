package by.telebook.telebookapplication.abonent;

import by.telebook.telebookapplication.city.City;
import by.telebook.telebookapplication.telNum.Number;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/abonent")
public class AbonentController {

    private final AbonentRepository abonentRepository;

    public AbonentController(AbonentRepository abonentRepository) {
        this.abonentRepository = abonentRepository;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Abonent abonent) {
        abonentRepository.save(abonent);
        return new ResponseEntity<>("Abonent " + abonent.getAbonentName() + " created", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/ById/{id}")
    public ResponseEntity<String> deleteAbonentById(@PathVariable Long id){
        return new ResponseEntity<>("Abonent with Id "+id+" successfully deleted.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/ByNameAndSurName/{abonentName}/{abonentSurname}")
    public ResponseEntity<String> deleteAbonentById(@PathVariable String abonentName,
                                                    @PathVariable String abonentSurname){
        abonentRepository.removeAbonentByAbonentNameAndAbonentSurname(abonentName,abonentSurname);
        return new ResponseEntity<>("Abonent with Name "+abonentName+" and Surname "+abonentSurname+" successfully deleted.", HttpStatus.OK);
    }

    @PostMapping(path = "/updateAbonentById/name/{id}")
    public ResponseEntity<String> updateAbonentNameById (@PathVariable Long id, @RequestParam String newName){
        Optional<Abonent> byId = abonentRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setAbonentName(newName);
            abonentRepository.save(byId.get());
            return new ResponseEntity<>("Abonent's Name with Id "+id+" successfully updated to "+newName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/updateAbonentById/surname/{id}")
    public ResponseEntity<String> updateAbonentSurNameById (@PathVariable Long id, @RequestParam String newSurname){
        Optional<Abonent> byId = abonentRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setAbonentSurname(newSurname);
            abonentRepository.save(byId.get());
            return new ResponseEntity<>("Abonent's Surname with Id "+id+" successfully updated to "+newSurname, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/updateAbonentById/city/{id}")
    public ResponseEntity<String> updateAbonentCityById (@PathVariable Long id, @RequestBody City city){
        Optional<Abonent> byId = abonentRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setCity(city);
            abonentRepository.save(byId.get());
            return new ResponseEntity<>("Abonent's City with Id "+id+" successfully updated to "+city, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/updateAbonentById/number/{id}")
    public ResponseEntity<String> updateAbonentNumberById (@PathVariable Long id, @RequestBody Number number){
        Optional<Abonent> byId = abonentRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setNumber(number);
            abonentRepository.save(byId.get());
            return new ResponseEntity<>("Abonent's City with Id "+id+" successfully updated to "+number, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
