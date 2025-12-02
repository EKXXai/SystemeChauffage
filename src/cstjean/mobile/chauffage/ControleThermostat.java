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

        if (actionDemandee.equals("Démarrer")) {
            if (temperature < 21) {
                serviceTermostat.DemarrerChauffage();
                return "OK – Démarrer chauffage";
            } else {
                return "Refusé";
            }
        }

        if (actionDemandee.equals("Arrêter")) {
            if (temperature >= 21) {
                serviceTermostat.ArreterChauffage();
                return "OK – Arrêter chauffage";
            } else {
                return "Refusé";
            }
        }

        return "Refusé";
    }

}
