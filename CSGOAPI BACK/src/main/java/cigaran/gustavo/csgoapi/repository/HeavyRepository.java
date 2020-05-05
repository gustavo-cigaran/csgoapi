package cigaran.gustavo.csgoapi.repository;

import cigaran.gustavo.csgoapi.model.HeavyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeavyRepository extends CrudRepository<HeavyEntity, Long> {

}