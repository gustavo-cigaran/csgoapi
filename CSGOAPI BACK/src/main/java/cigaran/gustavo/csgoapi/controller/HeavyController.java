package cigaran.gustavo.csgoapi.controller;
import cigaran.gustavo.csgoapi.model.HeavyEntity;
import cigaran.gustavo.csgoapi.repository.HeavyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Gustavo Cigaran
 * @since 2020
 */

@RestController
@RequestMapping("/heavys")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeavyController {

    @Autowired
    private HeavyRepository heavyRepository;

    @GetMapping
    public ResponseEntity<List<HeavyEntity>> findAll() {
        return new ResponseEntity<List<HeavyEntity>>(
                (List<HeavyEntity>) this.heavyRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HeavyEntity> findById(@PathVariable("id") long id) {
        if(this.heavyRepository.findById(id).isPresent()) {
            return new ResponseEntity<HeavyEntity>(
                    this.heavyRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<HeavyEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HeavyEntity> store(@RequestBody HeavyEntity heavyEntity) {
        return new ResponseEntity<HeavyEntity>(
                this.heavyRepository.save(heavyEntity),
                new HttpHeaders(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HeavyEntity> update(@PathVariable("id") long id,
                                              @RequestBody HeavyEntity heavyEntity) throws Exception {

        if(id == 0 || !this.heavyRepository.existsById(id)) {
            throw new Exception("Código não encontrado ou inexistente!");
        }

        return new ResponseEntity<HeavyEntity>(
                this.heavyRepository.save(heavyEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HeavyEntity> delete(@PathVariable("id") long id) {
        this.heavyRepository.deleteById(id);
        return new ResponseEntity<HeavyEntity>(new HttpHeaders(), HttpStatus.OK);
    }
}
