package cigaran.gustavo.csgoapi.controller;
import cigaran.gustavo.csgoapi.model.PistolEntity;
import cigaran.gustavo.csgoapi.repository.PistolRepository;
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
@RequestMapping("/pistols")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PistolController {

    @Autowired
    private PistolRepository pistolRepository;

    @GetMapping
    public ResponseEntity<List<PistolEntity>> findAll() {
        return new ResponseEntity<List<PistolEntity>>(
                (List<PistolEntity>) this.pistolRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PistolEntity> findById(@PathVariable("id") long id) {
        if(this.pistolRepository.findById(id).isPresent()) {
            return new ResponseEntity<PistolEntity>(
                    this.pistolRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<PistolEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PistolEntity> store(@RequestBody PistolEntity pistolEntity) {
        return new ResponseEntity<PistolEntity>(
                this.pistolRepository.save(pistolEntity),
                new HttpHeaders(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PistolEntity> update(@PathVariable("id") long id,
                                              @RequestBody PistolEntity pistolEntity) throws Exception {

        if(id == 0 || !this.pistolRepository.existsById(id)) {
            throw new Exception("Código não encontrado ou inexistente!");
        }

        return new ResponseEntity<PistolEntity>(
                this.pistolRepository.save(pistolEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PistolEntity> delete(@PathVariable("id") long id) {
        this.pistolRepository.deleteById(id);
        return new ResponseEntity<PistolEntity>(new HttpHeaders(), HttpStatus.OK);
    }
}
