package cstjean.mobile.chauffage;

/**
 * Service responsable de fournir la température actuelle.
 *
 * @author Artemii Bondar, Tamilla Ibragimova
 */
public interface ServiceTemperature {
    /**
     * Retourne la température actuelle en degrés Celsius.
     *
     * @return température mesurée
     */
    double getTemperature();
}
