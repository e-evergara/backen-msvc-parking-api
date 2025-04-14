package co.com.ecosoft.parking.backend_msvc_parking_api.domain.models;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.*;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;
import co.com.ecosoft.parking.backend_msvc_parking_api.test_base.TestBase;
import co.com.ecosoft.parking.backend_msvc_parking_api.testdatabuilder.RegistryTestDataBuilder;
import org.junit.jupiter.api.Test;

class RegistryTest {
    @Test
    void validateLicencePlateRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withLicensePlate(null);

        //Act - Assert
        TestBase.assertThrows(registryTestDataBuilder::build, ExceptionRegistryLicensePlate.class, RegistryDomainConstant.MESSAGE_LICENCE_PLATE_REQUIRED);
    }

    @Test
    void validateVehicleTypeRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType(null);

        //Act - Assert
        TestBase.assertThrows(registryTestDataBuilder::build, ExceptionRegistryNullVehicleType.class, RegistryDomainConstant.MESSAGE_VEHICLE_TYPE_REQUIRED);
    }

    @Test
    void validateVehicleTypeWrongDate() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        String vehicleType = "AUTO";
        registryTestDataBuilder.withVehicleType(vehicleType);

        //Act - Assert
        TestBase.assertThrows(registryTestDataBuilder::build, ExceptionRegistryVehicleTypeWrongDate.class,String.format(RegistryDomainConstant.MESSAGE_VEHICLE_TYPE_WRONG_DATA, vehicleType));
    }

    @Test
    void validateDisplacementNumber() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement("cero");

        //Act - Assert
        TestBase.assertThrows(registryTestDataBuilder::build, ExceptionRegistryDisplacementNotNumber.class, RegistryDomainConstant.MESSAGE_DISPLACEMENT_WRONG_DATA);
    }

    @Test
    void validateDisplacementRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement(null);

        //Act - Assert
        TestBase.assertThrows(registryTestDataBuilder::build, ExceptionRegistryDisplacementRequired.class, RegistryDomainConstant.MESSAGE_DISPLACEMENT_REQUIRED);
    }

}