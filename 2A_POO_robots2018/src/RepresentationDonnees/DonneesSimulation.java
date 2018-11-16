package RepresentationDonnees;

import java.util.ArrayList;

public class DonneesSimulation {
    private Carte carteSim;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;

    /**
     *
     * @param
     * @return
     */
    public void setCarte(Carte carte) {
        carteSim = carte;
    }

    /**
     *
     * @param
     * @return
     */
    public Carte getCarte() { return carteSim; }

    /**
     *
     * @param
     * @return
     */
    public void initRobotsList(int size) {
        robots = new ArrayList<Robot>(size);
    }

    /**
     *
     * @param
     * @return
     */
    public ArrayList<Robot> getRobotsList() {
        return robots;
    }

    /**
     *
     * @param
     * @return
     */
    public void initIncendiesList(int size) {
        incendies = new ArrayList<Incendie>(size);
    }

    /**
     *
     * @param
     * @return
     */
    public ArrayList<Incendie> getIncendiesList() {
        return incendies;
    }

}
