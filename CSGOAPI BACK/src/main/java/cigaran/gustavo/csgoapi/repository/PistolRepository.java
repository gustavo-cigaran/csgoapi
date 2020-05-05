package cigaran.gustavo.csgoapi.repository;

import cigaran.gustavo.csgoapi.model.PistolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PistolRepository extends CrudRepository<PistolEntity, Long> {

}