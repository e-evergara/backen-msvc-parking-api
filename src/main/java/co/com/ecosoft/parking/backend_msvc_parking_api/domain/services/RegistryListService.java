package co.com.ecosoft.parking.backend_msvc_parking_api.domain.services;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;

import java.util.List;

public class RegistryListService {
    private final RegistryPortRepository registryRepository;

    public RegistryListService(RegistryPortRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public List<Registry> listRegister() {
        return registryRepository.list();
    }
}
