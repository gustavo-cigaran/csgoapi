package cigaran.gustavo.csgoapi.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name = "heavy")
public class HeavyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_heavy")
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "team")
    private String team;

    @Column(name = "price")
    private String price;

    @Column(name = "munition")
    private String munition;

}
