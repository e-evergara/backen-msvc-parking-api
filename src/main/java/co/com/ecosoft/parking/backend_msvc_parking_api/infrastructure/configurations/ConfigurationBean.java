package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.configurations;

import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler.RegistryExitHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.handler.RegistryRecordHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.application.query.RegistryListHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryEntryService;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryExitService;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.RegistryListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {
    @Bean
    public RegistryRecordHandler registerHandler(RegistryEntryService registerService) {
        return new RegistryRecordHandler(registerService);
    }

    @Bean
    public RegistryEntryService registerService(RegistryPortRepository registryRepository){
        return new RegistryEntryService(registryRepository);
    }

    @Bean
    public RegistryListService registerListService(RegistryPortRepository registryRepository){
        return new RegistryListService(registryRepository);
    }
    @Bean
    public RegistryListHandler registerListHandler(RegistryListService registerListService) {
        return new RegistryListHandler(registerListService);
    }

    @Bean
    public RegistryExitService registerExitService(RegistryPortRepository registryRepository){
        return new RegistryExitService(registryRepository);
    }
    @Bean
    public RegistryExitHandler registerExitHandler(RegistryExitService registerExitService) {
        return new RegistryExitHandler(registerExitService);
    }
}