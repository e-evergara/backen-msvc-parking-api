package co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.exceptions;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String A_FAILURE_OCCURS_FAVOR_TO_CONTACT_THE_ADMINISTRATOR = "Ocurrió un error favor contactar al administrador.";
    private static final ConcurrentHashMap<String, Integer> STATE_CODES = new ConcurrentHashMap<>();

    public ExceptionHandler(){
        STATE_CODES.put(ExceptionRegistryDisplacementNotNumber.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionRegistryDisplacementRequired.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionVehicleDuplicity.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionRegistryLicensePlate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionRegistryNullVehicleType.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionRegistryVehicleTypeWrongDate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionInfrastructure> handleAllExceptions(ExceptionInfrastructure exception) {
        ResponseEntity<ExceptionInfrastructure> response;

        String name = exception.getClass().getSimpleName();
        String message = exception.message();
        Integer code = STATE_CODES.get(name);

        if (code != null) {
            ExceptionInfrastructure error = new ExceptionInfrastructure(name, message);
            response = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER .error(name, exception);
            ExceptionInfrastructure error = new ExceptionInfrastructure(name, A_FAILURE_OCCURS_FAVOR_TO_CONTACT_THE_ADMINISTRATOR);
            response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}