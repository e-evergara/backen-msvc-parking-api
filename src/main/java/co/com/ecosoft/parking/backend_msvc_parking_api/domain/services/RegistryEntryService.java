package co.com.ecosoft.parking.backend_msvc_parking_api.domain.services;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionLicensePlaceDateDay;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionNotSpaceTypeVehicle;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionVehicleDuplicity;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;

import java.util.Calendar;
import java.util.Date;

public class RegistryEntryService {
    private final RegistryPortRepository registryRepository;

    public RegistryEntryService(RegistryPortRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public Registry setEntry(Registry registry) {
        validatePrevious(registry);
        validateAmountVehicleType(registry.getVehicleType());
        validateLicencePlaceDateDay(registry.getLicensePlate(), registry.getDateArrival());
        return registryRepository.saveRegistro(registry);
    }

    private void validateLicencePlaceDateDay(String licensePlate, Date dateArrival) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateArrival);

        if ((licensePlate.toUpperCase().startsWith(RegistryDomainConstant.MESSAGE_RESTRICTED_PLATES_THAT_START_WITH_THE_LETTER))
                && (cal.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY)) {
            throw new ExceptionLicensePlaceDateDay(RegistryDomainConstant.MESSAGE_DAYS_NOT_ALLOWED);
        }
    }

    private void validateAmountVehicleType(String vehicleType) {
        int amountVehicleType = registryRepository.countVehicleType(vehicleType);

        if ((vehicleType.contentEquals(RegistryDomainConstant.VEHICLE_TYPE_VALUE_CAR) && RegistryDomainConstant.VEHICLE_TYPE_MAXIMUM_CAR == amountVehicleType)
                || (vehicleType.contentEquals(RegistryDomainConstant.VEHICLE_TYPE_VALUE_MOTORCYCLE) && RegistryDomainConstant.VEHICLE_TYPE_MAXIMUM_MOTORCYCLE == amountVehicleType)) {
            throw new ExceptionNotSpaceTypeVehicle(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, vehicleType));
        }

    }

    private void validatePrevious(Registry registry) {
        boolean exists = registryRepository.exists(registry);
        if (exists) {
            throw new ExceptionVehicleDuplicity(RegistryDomainConstant.MESSAGE_ALREADY_EXISTS_VEHICLE);
        }
    }
}
