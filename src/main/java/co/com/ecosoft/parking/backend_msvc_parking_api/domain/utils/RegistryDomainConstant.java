package co.com.ecosoft.parking.backend_msvc_parking_api.domain.utils;

public final class RegistryDomainConstant {

    private RegistryDomainConstant(){}

    public static final String MESSAGE_LICENCE_PLATE_REQUIRED = "La placa es un dato requerido.";
    public static final String MESSAGE_VEHICLE_TYPE_REQUIRED = "El campo tipo vehiculo es querido.";
    public static final String MESSAGE_VEHICLE_TYPE_WRONG_DATA = "El campo tipo vehiculo no tiene valor (%S) valido.";
    public static final String MESSAGE_DISPLACEMENT_REQUIRED = "El campo cilindraje es requerido.";
    public static final String MESSAGE_DISPLACEMENT_WRONG_DATA  = "El campo cilindraje debe ser numerico.";

    public static final String VEHICLE_TYPE_VALUE_CAR = "CAR";
    public static final String VEHICLE_TYPE_VALUE_MOTORCYCLE = "MOTORCYCLE";


    public static final String MESSAGE_RESTRICTED_PLATES_THAT_START_WITH_THE_LETTER = "A";
    public static final String MESSAGE_DAYS_NOT_ALLOWED = "El vehiculo no esta autorizado a ingresar.";
    public static final String MESSAGE_THERE_IS_NOT_SPACE = "En este momento no hay cupo para el tipo de vehiculo %S";
    public static final String MESSAGE_THIS_UNRESOLVED_VEHICLE = "El vehiculo no se encuentra en el parqueadero";
    public static final String MESSAGE_ALREADY_EXISTS_VEHICLE = "El vehiculo ya ha ingresado";

    public static final int VEHICLE_TYPE_MAXIMUM_CAR = 20;
    public static final int VEHICLE_TYPE_MAXIMUM_MOTORCYCLE = 10;
}
