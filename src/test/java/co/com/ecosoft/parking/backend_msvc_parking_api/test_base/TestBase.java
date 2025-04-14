package co.com.ecosoft.parking.backend_msvc_parking_api.test_base;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class TestBase {
    private static final String BUT_IT_WAS_LAUNCHED = " Pero fue lanzada ";
    private static final String THE_EXCEPTION_WAS_EXPECTED = "Se esperaba la exception ";

    public static <T> void assertThrows(Supplier<T> supplier, Class<? extends Exception> exception, String message) {
        try {
            supplier.get();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), THE_EXCEPTION_WAS_EXPECTED + exception.getCanonicalName() + BUT_IT_WAS_LAUNCHED
                                + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    public static void assertThrows(Thunk thunk, Class<? extends Exception> exception, String message) {
        try {
            thunk.execute();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), THE_EXCEPTION_WAS_EXPECTED + exception.getCanonicalName() + BUT_IT_WAS_LAUNCHED
                                + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    @FunctionalInterface
    public interface Thunk {
        void execute();
    }
}
