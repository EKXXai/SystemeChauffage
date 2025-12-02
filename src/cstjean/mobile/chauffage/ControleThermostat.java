package cstjean.mobile.chauffage;

public class ControleThermostat {


    private ServiceTemperature serviceTemperature;
    private ServiceTermostat serviceTermostat;

    public void setServiceTemperature(ServiceTemperature serviceTemperature) {
        this.serviceTemperature = serviceTemperature;
    }

    public void setServiceTermostat(ServiceTermostat serviceTermostat) {
        this.serviceTermostat = serviceTermostat;
    }

    public String Thermostat(String actionDemandee) {
        double temperature = Math.round(serviceTemperature.getTemperature());
        boolean demarrer = temperature < 21;
        boolean arreter = temperature > 21;

        if (demarrer) {
            if (actionDemandee.equals("Démarrer")) {
                serviceTermostat.DemarrerChauffage();
                return "OK – Démarrer chauffage";
            } else {
                return "Refusé";
            }
        }

        if (arreter) {
            if (actionDemandee.equals("Arrêter")) {
                serviceTermostat.ArreterChauffage();
                return "OK – Arrêter chauffage";
            } else {
                return "Refusé";
            }
        }

        return "Refusé";
    }
}
