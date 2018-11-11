package representation_donnees;

import java.util.ArrayList;

public class DonneesSimulation {
    private Carte carte_sim;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;


    public void setCarte(Carte carte) {
        carte_sim = carte;
    }

    public Carte getCarte() { return carte_sim; }

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
