package co.com.ecosoft.parking.backend_msvc_parking_api.integration;

import co.com.ecosoft.parking.backend_msvc_parking_api.application.command.RegisterCommand;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.controllers.ParkingController;
import co.com.ecosoft.parking.backend_msvc_parking_api.infrastructure.exceptions.ExceptionHandler;
import co.com.ecosoft.parking.backend_msvc_parking_api.testdatabuilder.RegisterCommandTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.junit.jupiter.MockitoExtension;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ParkingControllerTest {

    @Autowired
    private ExceptionHandler exceptionHandler;
    @Autowired
    private ParkingController parkingController;
    private MockMvc mockMvc;

    @BeforeEach
    void setupMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(parkingController, exceptionHandler).build();
    }

    @Test
    void listRegisterTest() throws Exception{
        // Arrange , Act y Assert
        mockMvc.perform(get("/registry").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getIntoVehicleTest() throws Exception {
        // Arrange
        RegisterCommandTestDataBuilder registerCommandTestDataBuilder = new RegisterCommandTestDataBuilder();
        RegisterCommand registerCommand = registerCommandTestDataBuilder.build();

        // Act y Assert
        mockMvc.perform(post("/registry").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(registerCommand))).andExpect(status().isOk());

    }

    @Test
    void getOutVehicleTest() throws Exception{
        // Arrange
        String  licensePlate = "B125";
        // Act y Assert
        mockMvc.perform(put("/registry/" + licensePlate).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private String jsonToString(RegisterCommand json) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(json);
    }
}