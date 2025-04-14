package co.com.ecosoft.parking.backend_msvc_parking_api.domain.sevices;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionLicensePlaceDateDay;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionNotSpaceTypeVehicle;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionRegistrationNotExist;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionVehicleDuplicity;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryEntryService;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryExitService;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;
import co.com.ecosoft.parking.backend_msvc_parking_api.testdatabuilder.RegistryTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServicesTest {
    private RegistryPortRepository portRegistryRepository;

    @BeforeEach
    void StartMocks() {
        this.portRegistryRepository = mock(RegistryPortRepository.class);
    }

    @Test
    void RegisterCarTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR);

        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        Registry registerCopy = registerEntryService.setEntry(registry);

        //Assert
        assertEquals(registerCopy.getId(), registry.getId());

    }

    @Test
    void RegisterMotorbikeTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDisplacement("500");

        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        Registry registerCopy = registerEntryService.setEntry(registry);

        //Assert
        assertEquals(registerCopy.getId(), registry.getId());
    }


    @Test
    void ParkingWithoutSpaceForCarTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR);

        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR)).thenReturn(20);

        //Act

        try {
            registerEntryService.setEntry(registry);
            fail();
        } catch (ExceptionNotSpaceTypeVehicle ex) {
            // Assert
            assertEquals(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR), ex.getMessage());
        }
    }

    @Test
    void ParkingWithoutSpaceForMotorbikeTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDisplacement("500");

        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)).thenReturn(10);

        //Act

        try {
            registerEntryService.setEntry(registry);
            fail();
        } catch (ExceptionNotSpaceTypeVehicle ex) {
            // Assert
            assertEquals(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE), ex.getMessage());
        }
    }

    @Test
    void doNotLetTheVehicleEnterForToTheRestrictionPlateTest() {
        //Arrange
        Calendar dateArrival = Calendar.getInstance();
        dateArrival.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        RegistryTestDataBuilder register = new RegistryTestDataBuilder().withLicensePlate("A123").withDateArrival(dateArrival.getTime());
        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);
        //Act

        try {
            registerEntryService.setEntry(registry);
            fail();
        } catch (ExceptionLicensePlaceDateDay ex) {
            // Assert
            assertEquals(RegistryDomainConstant.MESSAGE_DAYS_NOT_ALLOWED, ex.getMessage());
        }
    }

    @Test
    void vehicleDoesNotExistTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR);

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(null);

        //Act
        ExceptionRegistrationNotExist ex = assertThrows(ExceptionRegistrationNotExist.class, () -> registerExitService.setExit(registry.getLicensePlate()));

        // Assert
        assertEquals(RegistryDomainConstant.MESSAGE_THIS_UNRESOLVED_VEHICLE, ex.getMessage());
    }

    @Test
    void RegisterCarExistTest() {
        //Arrange
        String expected = "hola";
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR);

        Registry registry = register.build();

        RegistryEntryService registerEntryService = new RegistryEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        try {
            registerEntryService.setEntry(registry);
        } catch (ExceptionVehicleDuplicity ex) {
            // Assert
            assertEquals(RegistryDomainConstant.MESSAGE_ALREADY_EXISTS_VEHICLE, expected);
        }
    }

    @Test
    void RegisterChargeHourCarTest() {
        //Arrange
        int valueForHour = 1000;
        int hour = 6;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour), registry.getValue(), 0);

    }

    @Test
    void RegisterChargeHourMotorCycleTest() {
        //Arrange
        int valueForHour = 500;
        int hour = 6;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour), registry.getValue(), 0);

    }

    @Test
    void RegisterChargeHourDisplacementMaximumMotorCycleTest() {
        //Arrange
        int valueForHour = 500;
        int hour = 6;
        int valueAdditional = 2000;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("600");

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour) + valueAdditional, registry.getValue(), 0);

    }

    @Test
    void RegisterChargeDayCarTest() {
        //Arrange
        int valueDay = 8000;
        int hour = 9;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay, registry.getValue(), 0);

    }

    @Test
    void RegisterChargeDayDisplacementMaximumMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int hour = 9;
        int valueAdditional = 2000;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("600");

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay + valueAdditional, registry.getValue(), 0);

    }

    @Test
    void RegisterChargeDayMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int hour = 9;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay, registry.getValue(), 0);

    }

    @Test
    void RegisterChargeDayMoreHourCarTest() {
        //Arrange
        int valueDay = 8000;
        int valueHour = 1000;
        int hour = 27;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueHour * 3) + valueDay, registry.getValue(), 0);

    }

    @Test
    void RegisterChargeDayMoreHourMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int valueHour = 500;
        int hour = 27;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegistryExitService registerExitService = new RegistryExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueHour * 3) + valueDay, registry.getValue(), 0);

    }

}
