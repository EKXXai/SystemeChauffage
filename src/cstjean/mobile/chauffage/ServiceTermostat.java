package cstjean.mobile.chauffage;

/**
 * Service qui contrôle physiquement (ou logiquement) le chauffage.
 *
 * @author Artemii Bondar, Tamilla Ibragimova
 */
public interface ServiceTermostat {
    /**
     * Démarre le chauffage.
     */
    void demarrerChauffage();

    /**
     * Arrête le chauffage.
     */
    void arreterChauffage();
}
