package co.com.ecosoft.parking.backend_msvc_parking_api.domain.models;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;
import lombok.Data;

import java.util.Date;

@Data
public class Registry {
    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date  dateArrival;
    private Date dateDeparture;
    private long value;

    public Registry(Integer id, String vehicleType, String licensePlate, String displacement, Date dateArrival, Date dateDeparture, long value) {
        RegistryValidationArgument.validateLicencePlate(licensePlate, RegistryDomainConstant.MESSAGE_LICENCE_PLATE_REQUIRED);
        RegistryValidationArgument.validateNullVehicleType(vehicleType, RegistryDomainConstant. MESSAGE_VEHICLE_TYPE_REQUIRED);

        if(!vehicleType.contentEquals(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR) && !vehicleType.contentEquals(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)) {
            RegistryValidationArgument.validateVehicleTypeWrongDate(String.format(RegistryDomainConstant.MESSAGE_VEHICLE_TYPE_WRONG_DATA, vehicleType));
        }
        if (vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)) {
            RegistryValidationArgument.validateDisplacementRequired(displacement, RegistryDomainConstant.MESSAGE_DISPLACEMENT_REQUIRED);
            RegistryValidationArgument.validateDisplacementNumber(displacement, RegistryDomainConstant.MESSAGE_DISPLACEMENT_WRONG_DATA);
        }

        this.id = id;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.displacement = displacement;
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
        this.value = value;
    }
}
