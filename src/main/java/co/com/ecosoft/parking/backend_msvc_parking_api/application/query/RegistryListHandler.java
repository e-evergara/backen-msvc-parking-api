package co.com.ecosoft.parking.backend_msvc_parking_api.application.query;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryListService;

import java.util.List;

public class RegistryListHandler {
    private final RegistryListService listService;

    public RegistryListHandler(RegistryListService listService) {
        this.listService = listService;
    }

    public List<Registry> handler(){
        return  this.listService.listRegister();
    }
}
