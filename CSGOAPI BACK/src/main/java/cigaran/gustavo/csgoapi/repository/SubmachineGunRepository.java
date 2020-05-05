package cigaran.gustavo.csgoapi.repository;

import cigaran.gustavo.csgoapi.model.SubmachineGunEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmachineGunRepository extends CrudRepository<SubmachineGunEntity, Long> {

}