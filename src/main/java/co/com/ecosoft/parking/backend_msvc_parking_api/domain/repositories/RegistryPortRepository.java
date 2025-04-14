package co.com.ecosoft.parking.backend_msvc_parking_api.domain.repositories;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;

import java.util.List;

public interface RegistryPortRepository {
    /**
     *Registra el ingreso de un vehiculo
     *
     * @param  Objeto que tiene la información del registro a crear
     */
    Registry saveRegistro(Registry registry);

    /**
     * Permite listar los registros de vehículos
     *
     * @return
     */
    List<Registry> list();

    /**
     * Permite contar la lista de vehículos de un tipo
     *
     * @param String con el tipo de vehículo
     * @return int con la candida de vehículo
     */
    int countVehicleType(String vehicleType);

    /**
     * Permite buscar un vehiculo por su placa
     *  @param  Placa del vehiculo
     * @return Objeto de registro
     */
    Registry findByLicensePlate(String licensePlate);

    /**
     * Permite determinar si previamente se registro la placa
     * @param registry
     * @return si existe o no
     */
    boolean exists(Registry registry);
}
