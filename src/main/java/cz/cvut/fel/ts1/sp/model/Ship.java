package cz.cvut.fel.ts1.sp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String planet;

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Temporal(TemporalType.DATE)
    private Date prodDate;

    private Boolean isUsed;

    private Double speed;

    private Integer crewSize;

    private Double rating;

    private Double price;
}