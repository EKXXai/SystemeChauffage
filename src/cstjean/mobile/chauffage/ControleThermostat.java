package cstjean.mobile.chauffage;

/**
 * Contrôleur qui décide si le chauffage doit démarrer ou s'arrêter
 * en fonction de la température retournée par ServiceTemperature
 * et de l'action demandée ("Démarrer" ou "Arrêter").
 *
 * @author Artemii Bondar, Tamilla Ibragimova
 */
public class ControleThermostat {
    /** Service qui fournit la température actuelle. */
    private ServiceTemperature serviceTemperature;

    /** Service qui fournit la température actuelle. */
    private ServiceTermostat serviceTermostat;

    /** Dernière température acceptée (0 si aucune). */
    private Double derniereTemperature = null;

    /**
     * Injection du service de température.
     *
     * @param serviceTemperature le service qui fournit la température
     */
    public void setServiceTemperature(ServiceTemperature serviceTemperature) {
        this.serviceTemperature = serviceTemperature;
    }

    /**
     * Injection du service de thermostat.
     *
     * @param serviceTermostat le service qui permet de démarrer/arrêter le chauffage
     */
    public void setServiceTermostat(ServiceTermostat serviceTermostat) {
        this.serviceTermostat = serviceTermostat;
    }

    /**
     * Méthode principale du contrôleur. Elle reçoit une action demandée
     * par l'utilisateur ou le système ("Démarrer" ou "Arrêter").
     * - Si la température arrondie est < 21°C alors le chauffage peut démarrer.
     * - Si la température arrondie est > 21°C alors le chauffage peut s'arrêter.
     * - Si la température est exactement 21°C → aucune action.
     *
     * @param actionDemandee "Démarrer" ou "Arrêter"
     */
    public void thermostat(String actionDemandee) {
        double temperature = serviceTemperature.getTemperature();

        // Si c'est la première valeur → on l'accepte automatiquement.
        if (derniereTemperature == null) {
            derniereTemperature = temperature;
        }

        // Vérifier la variation maximale de 0.5°C
        if (Math.abs(temperature - derniereTemperature) > 0.5) {
            // Variation trop grande → on ignore l'action
            return;
        }

        // On accepte la valeur → on la remplace
        derniereTemperature = temperature;

        boolean demarrer = temperature < 21;    // Trop froid → possibilité de démarrer
        boolean arreter = temperature >= 21;    // Trop chaud → possibilité d'arrêter

        // Si on peut démarrer et que l'utilisateur a effectivement demandé de démarrer
        if (demarrer) {
            if (actionDemandee.equals("Démarrer")) {
                serviceTermostat.demarrerChauffage();
            }
        }
        // Si on peut arrêter et que l'utilisateur a demandé d'arrêter
        if (arreter) {
            if (actionDemandee.equals("Arrêter")) {
                serviceTermostat.arreterChauffage();
            }
        }
    }
}