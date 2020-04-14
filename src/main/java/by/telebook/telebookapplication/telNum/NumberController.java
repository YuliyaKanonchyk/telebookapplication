package by.telebook.telebookapplication.telNum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/number")
public class NumberController {
    private NumberRepository numberRepository;

    public NumberController(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @PostMapping
    public ResponseEntity<Number> create(@Valid @RequestBody Number number) {
        return new ResponseEntity<>(numberRepository.save(number), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteId/{id}")
    public ResponseEntity<String> deleteNumber(@PathVariable Long id) {
        Optional<Number> byId = numberRepository.findById(id);
        if (byId.isPresent()) {
            numberRepository.deleteById(id);
            return new ResponseEntity<>(byId.get().getNumber() + " Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Number with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<Number> findNumberById(@PathVariable Long id) {
        Optional<Number> byId = numberRepository.findById(id);
        if (byId.isPresent()) {
            return new ResponseEntity<>(byId.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findByNumber/{number}")
    public ResponseEntity<Number> findNumberByName(@PathVariable String number) {
        Optional<Number> byNumber = numberRepository.findByNumber(number);
        if (byNumber.isPresent()) {
            return new ResponseEntity<>(byNumber.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findNumberByTeleBookId/{id}")
    public ResponseEntity<List<Number>> findNumbersByTeleBookId (@PathVariable Long id){
         Optional<List<Number>> allByTeleBook_id = numberRepository.findNumbersByTeleBook_Id(id);
        if (allByTeleBook_id.isPresent()) {
            return new ResponseEntity<>(allByTeleBook_id.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(allByTeleBook_id.get(), HttpStatus.NOT_FOUND);
    }
}
