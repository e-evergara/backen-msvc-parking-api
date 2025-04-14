package co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryExitService;

public class RegistryExitHandler {

    private  final RegistryExitService registryExitService;


    public RegistryExitHandler(RegistryExitService registryExitService) {
        this.registryExitService = registryExitService;
    }

    public void handler(String licensePlate){
        this.registryExitService.setExit(licensePlate);
    }
}
