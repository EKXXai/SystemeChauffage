package cstjean.mobile.chauffage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

        controle.thermostat("Démarrer");

        verify(mockTermostat, times(1)).demarrerChauffage();
        verify(mockTermostat, never()).arreterChauffage();
    }

    @Test
    public void testArreter() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(19.0);

        controle.thermostat("Arrêter");

        verify(mockTermostat, never()).demarrerChauffage();
        verify(mockTermostat, never()).arreterChauffage();
    }

    @Test
    public void testArreterTemperature22() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(22.0);

        controle.thermostat("Arrêter");

        verify(mockTermostat, times(1)).arreterChauffage();
        verify(mockTermostat, never()).demarrerChauffage();
    }

    @Test
    public void testRefuse() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(22.0);

        controle.thermostat("Démarrer");

        verify(mockTermostat, never()).demarrerChauffage();
        verify(mockTermostat, never()).arreterChauffage();
    }

    @Test
    public void testActionInconnue() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(22.0);

        String result = controle.Thermostat("Pause");

        verify(mockTermostat, never()).DemarrerChauffage();
        verify(mockTermostat, never()).ArreterChauffage();
        assertEquals("Refusé", result);
    }
}
