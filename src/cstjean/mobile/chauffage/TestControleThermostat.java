package cstjean.mobile.chauffage;

import org.junit.Test;

import static org.mockito.Mockito.*;


public class TestControleThermostat {
    ControleThermostat controle = new ControleThermostat();

    @Test
    public void testDemarrer() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(19.0);

        controle.Thermostat("Démarrer");

        verify(mockTermostat, times(1)).DemarrerChauffage();
        verify(mockTermostat, never()).ArreterChauffage();
    }

    @Test
    public void testArreter() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(19.0);

        controle.Thermostat("Arrêter");

        verify(mockTermostat, never()).DemarrerChauffage();
        verify(mockTermostat, never()).ArreterChauffage();
    }

    @Test
    public void testArreterTemperature22() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(22.0);

        controle.Thermostat("Arrêter");

        verify(mockTermostat, times(1)).ArreterChauffage();
        verify(mockTermostat, never()).DemarrerChauffage();
    }

    @Test
    public void testRefuse() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(22.0);

        controle.Thermostat("Démarrer");

        verify(mockTermostat, never()).DemarrerChauffage();
        verify(mockTermostat, never()).ArreterChauffage();
    }
}
