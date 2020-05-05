package cigaran.gustavo.csgoapi.repository;

import cigaran.gustavo.csgoapi.model.RifleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RifleRepository extends CrudRepository<RifleEntity, Long> {

}