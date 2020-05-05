package cigaran.gustavo.csgoapi.controller;
import cigaran.gustavo.csgoapi.model.RifleEntity;
import cigaran.gustavo.csgoapi.repository.RifleRepository;
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
@RequestMapping("/rifles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RifleController {

    @Autowired
    private RifleRepository rifleRepository;

    @GetMapping
    public ResponseEntity<List<RifleEntity>> findAll() {
        return new ResponseEntity<List<RifleEntity>>(
                (List<RifleEntity>) this.rifleRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RifleEntity> findById(@PathVariable("id") long id) {
        if(this.rifleRepository.findById(id).isPresent()) {
            return new ResponseEntity<RifleEntity>(
                    this.rifleRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<RifleEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<RifleEntity> store(@RequestBody RifleEntity rifleEntity) {
        return new ResponseEntity<RifleEntity>(
                this.rifleRepository.save(rifleEntity),
                new HttpHeaders(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RifleEntity> update(@PathVariable("id") long id,
                                              @RequestBody RifleEntity rifleEntity) throws Exception {

        if(id == 0 || !this.rifleRepository.existsById(id)) {
            throw new Exception("Código não encontrado ou inexistente!");
        }

        return new ResponseEntity<RifleEntity>(
                this.rifleRepository.save(rifleEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RifleEntity> delete(@PathVariable("id") long id) {
        this.rifleRepository.deleteById(id);
        return new ResponseEntity<RifleEntity>(new HttpHeaders(), HttpStatus.OK);
    }
}
