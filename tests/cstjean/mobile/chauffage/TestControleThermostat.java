package cstjean.mobile.chauffage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * Tests unitaires pour la classe ControleThermostat.
 */

public class TestControleThermostat {

    /** Instance du contrôleur utilisée pour les tests. */

    private final ControleThermostat controle = new ControleThermostat();

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
    public void testPremierAppelAccepteToujours() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(30.0);

        controle.thermostat("Arrêter");

        verify(mockTermostat, times(1)).arreterChauffage();
    }

    @Test
    public void testVariationTropGrandeIgnore() {
        ServiceTemperature mockTemp = mock(ServiceTemperature.class);
        ServiceTermostat mockTermostat = mock(ServiceTermostat.class);

        controle.setServiceTemperature(mockTemp);
        controle.setServiceTermostat(mockTermostat);

        when(mockTemp.getTemperature()).thenReturn(20.0);
        controle.thermostat("Démarrer");   // première — acceptée

        when(mockTemp.getTemperature()).thenReturn(21.0); // saut = 1.0 > 0.5
        controle.thermostat("Arrêter");

        verify(mockTermostat, times(1)).demarrerChauffage(); // seulement le premier!
        verify(mockTermostat, never()).arreterChauffage();
    }
}
