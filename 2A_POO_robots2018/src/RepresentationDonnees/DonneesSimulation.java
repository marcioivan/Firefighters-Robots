package RepresentationDonnees;

import java.util.ArrayList;

public class DonneesSimulation {
    private Carte carteSim;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;


    public void setCarte(Carte carte) {
        carteSim = carte;
    }

    public Carte getCarte() { return carteSim; }

    public void initRobotsList(int size) {
        robots = new ArrayList<Robot>(size);
    }

    public ArrayList<Robot> getRobotsList() {
        return robots;
    }

    public void initIncendiesList(int size) {
        incendies = new ArrayList<Incendie>(size);
    }

    public ArrayList<Incendie> getIncendiesList() {
        return incendies;
    }

}
