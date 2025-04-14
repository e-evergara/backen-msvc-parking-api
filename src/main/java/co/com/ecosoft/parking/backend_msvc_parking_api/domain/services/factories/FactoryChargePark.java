package co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.factories;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionRegistryVehicleTypeWrongDate;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;

public final class FactoryChargePark {

    private FactoryChargePark() {
    }

    public static ChargeParking getInstance(String vehicleType) {
        if(vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR)){
            return new ChargeCar();
        }
        else if(vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE) ){
            return new ChargeMotorCycle();
        }
        else{
            throw new ExceptionRegistryVehicleTypeWrongDate(String.format(RegistryDomainConstant.MESSAGE_VEHICLE_TYPE_WRONG_DATA, vehicleType));
        }
    }
}