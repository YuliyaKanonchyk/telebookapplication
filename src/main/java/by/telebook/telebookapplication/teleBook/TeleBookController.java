package by.telebook.telebookapplication.teleBook;

import by.telebook.telebookapplication.telNum.Number;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/teleBook")
public class TeleBookController {
    private TeleBookRepository teleBookRepository;

    public TeleBookController(TeleBookRepository teleBookRepository) {
        this.teleBookRepository = teleBookRepository;
    }

    @PostMapping
    public ResponseEntity<TeleBook> create(@Valid @RequestBody TeleBook teleBook) {
        return new ResponseEntity<>(teleBookRepository.save(teleBook), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteId/{id}")
    public ResponseEntity<String> deleteTeleBookById(@PathVariable Long id) {
        final Optional<TeleBook> byId = teleBookRepository.findById(id);
        if (byId.isPresent()) {
            teleBookRepository.deleteById(id);
            return new ResponseEntity<>(byId.get() + " Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("TeleBook with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/teleBook/{id}")
    public ResponseEntity<String> updateTeleBookNameById (@RequestParam String name, @PathVariable Long id){
        final Optional<TeleBook> byId = teleBookRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setName(name);
            return new ResponseEntity<>(byId.get().getName()+" Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("TeleBook with ID-" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findAllNumbers/{id}")
    public ResponseEntity<List<Number>> findAllNumbers(@PathVariable Long id){
        final Optional<List<Number>> byIdOrderByNumberNumber = teleBookRepository.findByIdOrderByNumberNumber(id);
        if (byIdOrderByNumberNumber.isPresent()) {
            return new ResponseEntity<>(byIdOrderByNumberNumber.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
