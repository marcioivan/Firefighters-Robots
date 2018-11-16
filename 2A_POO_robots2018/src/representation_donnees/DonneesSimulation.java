package representation_donnees;

import java.util.ArrayList;

public class DonneesSimulation {
    private Carte carte_sim;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;

    /**
     *
     * @param
     * @return
     */
    public void setCarte(Carte carte) {
        carte_sim = carte;
    }

    /**
     *
     * @param
     * @return
     */
    public Carte getCarte() { return carte_sim; }

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
