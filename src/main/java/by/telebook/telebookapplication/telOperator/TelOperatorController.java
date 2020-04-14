package by.telebook.telebookapplication.telOperator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/telOperator")
public class TelOperatorController {
    private TelOperatorRepository telOperatorRepository;

    public TelOperatorController(TelOperatorRepository telOperatorRepository) {
        this.telOperatorRepository = telOperatorRepository;
    }

    @PostMapping
    public ResponseEntity<TelOperator> create(@Valid @RequestBody TelOperator telOperator) {
        return new ResponseEntity<>(telOperatorRepository.save(telOperator), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteId/{id}")
    public ResponseEntity<String> deleteTelOperator(@PathVariable Long id) {
        Optional<TelOperator> byId = telOperatorRepository.findById(id);
        if (byId.isPresent()) {
            telOperatorRepository.deleteById(id);
            return new ResponseEntity<>(byId.get().getName() + " Deleted"
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>("TelOperator with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/telOperator/{id}")
    public ResponseEntity<String> updateTelOperatorNameById(@RequestParam String name, @PathVariable Long id) {
        Optional<TelOperator> byId = telOperatorRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setName(name);
            telOperatorRepository.save(byId.get());
            return new ResponseEntity<>(byId.get() + " Updated"
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>("TelOperator with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/find/{name}")
    public ResponseEntity<TelOperator> findTelOperatorByName(@PathVariable String name) {
        Optional<TelOperator> byName = telOperatorRepository.findByName(name);
        if (byName.isPresent()) {
            return new ResponseEntity<>(byName.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
