package co.com.ecosoft.parking.backend_msvc_parking_api.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand implements Serializable {
    private static final long serialVersionUID = 42L;

    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date dateArrival;
    private Date dateDeparture;
    private long value;
}