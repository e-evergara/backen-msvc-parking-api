package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.adapter;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;
import co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories.RegistryPortRepository;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.mappers.RegistryMapper;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.entities.RegistryEntity;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.repositories.RepositoryParkingJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdapterParkingRepository implements RegistryPortRepository {

    private final RepositoryParkingJPA repositoryParkingJPA;
    private final RegistryMapper registryMapper;

    public AdapterParkingRepository(RepositoryParkingJPA repositoryParkingJPA, RegistryMapper registryMapper){
        this.repositoryParkingJPA = repositoryParkingJPA;
        this.registryMapper = registryMapper;
    }

    @Override
    public Registry saveRegistro(Registry registry) {
        RegistryEntity registryEntity = repositoryParkingJPA.save(registryMapper.convertToEntity(registry));
        return registryMapper.convertToDomain(registryEntity);
    }

    @Override
    public List<Registry> list() {
        final List<RegistryEntity> listRegistryEntity = repositoryParkingJPA.listAll();
        return registryMapper.listConvertToDomain(listRegistryEntity);
    }

    @Override
    public int countVehicleType(String vehicleType) {
        return repositoryParkingJPA.countVehicleType(vehicleType);
    }

    @Override
    public Registry findByLicensePlate(String licensePlate) {
        RegistryEntity registryEntity = repositoryParkingJPA.findByLicensePlate(licensePlate);
        return registryEntity == null? null : registryMapper.convertToDomain(registryEntity);
    }

    @Override
    public boolean exists(Registry registry) {
        return repositoryParkingJPA.exists(registry.getLicensePlate());
    }
}