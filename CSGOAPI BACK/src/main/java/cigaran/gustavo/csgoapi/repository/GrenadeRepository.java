package cigaran.gustavo.csgoapi.repository;

import cigaran.gustavo.csgoapi.model.GrenadeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrenadeRepository extends CrudRepository<GrenadeEntity, Long> {

}