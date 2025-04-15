package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.controllers;

import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.RegisterCommand;
import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler.RegistryExitHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler.RegistryRecordHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.application.query.RegistryListHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registry")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ParkingController {

    private final RegistryRecordHandler registryRecordHandler;
    private final RegistryExitHandler registryExitHandler;
    private final RegistryListHandler registryListHandler;

    public ParkingController(RegistryRecordHandler registryRecordHandler, RegistryExitHandler registryExitHandler, RegistryListHandler registryListHandler) {
        this.registryRecordHandler = registryRecordHandler;
        this.registryExitHandler = registryExitHandler;
        this.registryListHandler = registryListHandler;
    }


    @GetMapping
    public List<Registry> list() {
        return this.registryListHandler.handler();
    }

    @PostMapping
    public void setEntry(@RequestBody RegisterCommand registerCommand) {
        this.registryRecordHandler.handler(registerCommand);
    }

    @PutMapping("/{licensePlate}")
    public void setOutPut(@PathVariable("licensePlate") String licensePlate) {
        this.registryExitHandler.handler(licensePlate);
    }
}
