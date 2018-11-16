package simulables;

import gui.GUISimulator;
import representation_donnees.Carte;

public class CarteSimulable {
    public static Carte carte_sim;
    private static double square_side;

    /**
     *
     * @param
     * @return
     */
    public static Carte getCarteSim() {return CarteSimulable.carte_sim;}

    /**
     *
     * @param
     * @return
     */
    public static void setSquareSide(double l) {
        CarteSimulable.square_side = l;
    }

    /**
     *
     * @param
     * @return
     */
    public static double getSquareSide() {
        return CarteSimulable.square_side;
    }

    /**
     *
     * @param
     * @return
     */
    public static void draw(GUISimulator gui, Carte carte) {
        carte_sim = carte;

        CaseSimulable curr_case;
        for(int i = 0; i < carte.getNbLignes(); i++) {
            for(int j = 0; j < carte.getNbColonnes(); j++) {
                curr_case = new CaseSimulable(gui, carte.getCase(i, j));
                curr_case.draw();
            }
        }
    }
}
