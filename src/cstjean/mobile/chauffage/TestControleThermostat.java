package cstjean.mobile.chauffage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestControleThermostat {

    @Mock
    ServiceTemperature temperatureService;

    @Mock
    ServiceTermostat thermostatService;

    @InjectMocks
    ControleThermostat controle; // will use the mocks in constructor

    @Test
    void DemarrerChauffage_OK_quandTemp19EtDemarrer() {
        // arrange
        when(temperatureService.getTemperature()).thenReturn(19.0);

        // act
        String message = controle.Thermostat("Demarrer");

        // assert
        assertEquals("OK – Démarrer chauffage", message);
        verify(thermostatService).DemarrerChauffage();
        verify(thermostatService, never()).ArreterChauffage();
    }

    @Test
    void Refuse_quandTemp19EtArreter() {
        when(temperatureService.getTemperature()).thenReturn(19.0);

        String message = controle.Thermostat("Arreter");

        assertEquals("Refusé", message);
        verify(thermostatService, never()).DemarrerChauffage();
        verify(thermostatService, never()).ArreterChauffage();
    }

    @Test
    void ArreterChauffage_OK_quandTemp22EtArreter() {
        when(temperatureService.getTemperature()).thenReturn(22.0);

        String message = controle.Thermostat("Arreter");

        assertEquals("OK – Arrêter chauffage", message);
        verify(thermostatService).ArreterChauffage();
        verify(thermostatService, never()).DemarrerChauffage();
    }

    @Test
    void Refuse_quandTemp22EtDemarrer() {
        when(temperatureService.getTemperature()).thenReturn(22.0);

        String message = controle.Thermostat("Demarrer");

        assertEquals("Refusé", message);
        verify(thermostatService, never()).DemarrerChauffage();
        verify(thermostatService, never()).ArreterChauffage();
    }
}
