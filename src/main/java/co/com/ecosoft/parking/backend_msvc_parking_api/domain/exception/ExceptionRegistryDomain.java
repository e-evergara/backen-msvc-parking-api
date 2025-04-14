package co.com.ecosoft.parking.backend_msvc_parking_api.domain.exception;

import java.io.Serial;

public class ExceptionRegistryDomain extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExceptionRegistryDomain(String message) {
        super(message);
    }
}
