package co.com.ecosoft.parking.backend_msvc_parking_api.domain.services;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.ExceptionRegistrationNotExist;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.factories.ChargeParking;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.factories.FactoryChargePark;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils.RegistryDomainConstant;

import java.util.Date;

public class RegistryExitService {
    private final RegistryPortRepository registryRepository;

    // Inyección de dependencia por constructor
    public RegistryExitService(RegistryPortRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public void setExit(String licensePlate) {
        Registry registry = registryRepository.findByLicensePlate(licensePlate);

        if (registry == null) {
            throw new ExceptionRegistrationNotExist(RegistryDomainConstant.MESSAGE_THIS_UNRESOLVED_VEHICLE);
        }
        chargePark(registry);
        registryRepository.saveRegistro(registry);
    }

    private void chargePark(Registry registry) {
        registry.setDateDeparture(new Date());
        ChargeParking chargePark = FactoryChargePark.getInstance(registry.getVehicleType());
        chargePark.setCharge(registry);
    }

}
