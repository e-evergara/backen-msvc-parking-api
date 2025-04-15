package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGISTRIES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistryEntity {
    @Id
    @TableGenerator(name = "REGISTRIES_GENERATOR", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "REGISTRIES_GENERATOR")
    @Column
    private Integer id;
    @Column
    private String vehicleType;
    @Column
    private String licensePlate;
    @Column
    private String displacement;
    @Column
    private Date dateArrival;
    @Column
    private Date dateDeparture;
    @Column
    private long amount;

}