package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.mappers;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.entities.RegistryEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistryMapper {

    public Registry convertToDomain(RegistryEntity registryEntity){

        return new Registry(registryEntity.getId(),registryEntity.getVehicleType(),registryEntity.getLicensePlate()
                ,registryEntity.getDisplacement(), registryEntity.getDateArrival(), registryEntity.getDateDeparture()
                ,registryEntity.getAmount());

    }

    public RegistryEntity convertToEntity(Registry registry){
        return new RegistryEntity(registry.getId(),registry.getVehicleType(),registry.getLicensePlate()
                ,registry.getDisplacement(),registry.getDateArrival(),registry.getDateDeparture(), registry.getValue());
    }

    public List<Registry> listConvertToDomain(List<RegistryEntity> listRegistryEntity) {
        final List<Registry> listRegistry = new ArrayList<>();

        listRegistryEntity.forEach(registryEntity -> listRegistry.add(new Registry(registryEntity.getId()
                ,registryEntity.getVehicleType(),registryEntity.getLicensePlate()
                ,registryEntity.getDisplacement(),registryEntity.getDateArrival(),registryEntity.getDateDeparture()
                ,registryEntity.getAmount())));

        return  listRegistry;
    }
}