package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.repositories;

import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.persistences.entities.RegistryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RepositoryParkingJPA extends CrudRepository<RegistryEntity, Integer> {

    @Query("SELECT COUNT(id) FROM RegistryEntity r WHERE r.vehicleType = :vehicleType AND r.dateDeparture IS NULL")
    int countVehicleType(@Param("vehicleType") String vehicleType);

    @Query("SELECT r FROM RegistryEntity r WHERE r.licensePlate = :licensePlate AND r.dateDeparture IS NULL")
    RegistryEntity findByLicensePlate(@Param("licensePlate") String licensePlate);

    @Query("SELECT r FROM RegistryEntity r")
    List<RegistryEntity> listAll();

    @Query("SELECT CASE WHEN COUNT(r.id) > 0 THEN true ELSE false END FROM RegistryEntity r WHERE r.licensePlate = :licensePlate AND r.dateDeparture IS NULL")
    boolean exists(@Param("licensePlate") String licensePlate);
}
