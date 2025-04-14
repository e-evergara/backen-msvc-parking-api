package co.com.ecosoft.parking.backend_msvc_parking_api.domain.models;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.*;

public final class RegistryValidationArgument {

    private RegistryValidationArgument(){}

    public static void validateLicencePlate(String value, String message) {
        if (value == null) {
            throw new ExceptionRegistryLicensePlate(message);
        }
    }

    public static void validateNullVehicleType(String value, String message) {
        if (value == null) {
            throw new ExceptionRegistryNullVehicleType(message);
        }
    }

    public static void validateVehicleTypeWrongDate(String message) {
        throw new ExceptionRegistryVehicleTypeWrongDate(message);
    }

    public static void validateDisplacementNumber(String value, String message) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            throw new ExceptionRegistryDisplacementNotNumber(message);
        }
    }

    public static void validateDisplacementRequired(String value, String message) {
        if (value == null) {
            throw new ExceptionRegistryDisplacementRequired(message);
        }
    }
}