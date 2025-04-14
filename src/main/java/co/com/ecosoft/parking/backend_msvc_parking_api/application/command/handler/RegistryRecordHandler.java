package co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler;

import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.RegisterCommand;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryEntryService;

import java.util.Date;

public class RegistryRecordHandler {

    private final RegistryEntryService registryEntryService;

    public RegistryRecordHandler(RegistryEntryService registryEntryService) {
        this.registryEntryService = registryEntryService;
    }

    public void handler(RegisterCommand registerCommand){
        this.registryEntryService.setEntry(new Registry(registerCommand.getId(), registerCommand.getVehicleType(), registerCommand.getLicensePlate()
                ,registerCommand.getDisplacement(),new Date()
                ,registerCommand.getDateDeparture(),registerCommand.getValue()));
    }
}
