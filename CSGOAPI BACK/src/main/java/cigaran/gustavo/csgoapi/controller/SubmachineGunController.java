package cigaran.gustavo.csgoapi.controller;
import cigaran.gustavo.csgoapi.model.SubmachineGunEntity;
import cigaran.gustavo.csgoapi.repository.SubmachineGunRepository;
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
@RequestMapping("/smgs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubmachineGunController {

    @Autowired
    private SubmachineGunRepository submachineGunRepository;

    @GetMapping
    public ResponseEntity<List<SubmachineGunEntity>> findAll() {
        return new ResponseEntity<List<SubmachineGunEntity>>(
                (List<SubmachineGunEntity>) this.submachineGunRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SubmachineGunEntity> findById(@PathVariable("id") long id) {
        if(this.submachineGunRepository.findById(id).isPresent()) {
            return new ResponseEntity<SubmachineGunEntity>(
                    this.submachineGunRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<SubmachineGunEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SubmachineGunEntity> store(@RequestBody SubmachineGunEntity submachineGunEntity) {
        return new ResponseEntity<SubmachineGunEntity>(
                this.submachineGunRepository.save(submachineGunEntity),
                new HttpHeaders(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SubmachineGunEntity> update(@PathVariable("id") long id,
                                              @RequestBody SubmachineGunEntity submachineGunEntity) throws Exception {

        if(id == 0 || !this.submachineGunRepository.existsById(id)) {
            throw new Exception("Código não encontrado ou inexistente!");
        }

        return new ResponseEntity<SubmachineGunEntity>(
                this.submachineGunRepository.save(submachineGunEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SubmachineGunEntity> delete(@PathVariable("id") long id) {
        this.submachineGunRepository.deleteById(id);
        return new ResponseEntity<SubmachineGunEntity>(new HttpHeaders(), HttpStatus.OK);
    }
}
