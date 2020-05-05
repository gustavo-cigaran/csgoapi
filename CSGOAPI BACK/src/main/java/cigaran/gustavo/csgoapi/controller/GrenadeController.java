package cigaran.gustavo.csgoapi.controller;
import cigaran.gustavo.csgoapi.model.GrenadeEntity;
import cigaran.gustavo.csgoapi.repository.GrenadeRepository;
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
@RequestMapping("/grenades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrenadeController {

    @Autowired
    private GrenadeRepository grenadeRepository;

    @GetMapping
    public ResponseEntity<List<GrenadeEntity>> findAll() {
        return new ResponseEntity<List<GrenadeEntity>>(
                (List<GrenadeEntity>) this.grenadeRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GrenadeEntity> findById(@PathVariable("id") long id) {
        if(this.grenadeRepository.findById(id).isPresent()) {
            return new ResponseEntity<GrenadeEntity>(
                    this.grenadeRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<GrenadeEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<GrenadeEntity> store(@RequestBody GrenadeEntity grenadeEntity) {
        return new ResponseEntity<GrenadeEntity>(
                this.grenadeRepository.save(grenadeEntity),
                new HttpHeaders(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GrenadeEntity> update(@PathVariable("id") long id,
                                              @RequestBody GrenadeEntity grenadeEntity) throws Exception {

        if(id == 0 || !this.grenadeRepository.existsById(id)) {
            throw new Exception("Código não encontrado ou inexistente!");
        }

        return new ResponseEntity<GrenadeEntity>(
                this.grenadeRepository.save(grenadeEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GrenadeEntity> delete(@PathVariable("id") long id) {
        this.grenadeRepository.deleteById(id);
        return new ResponseEntity<GrenadeEntity>(new HttpHeaders(), HttpStatus.OK);
    }
}
