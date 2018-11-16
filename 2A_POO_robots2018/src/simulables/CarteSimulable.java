package simulables;

import gui.GUISimulator;
import RepresentationDonnees.Carte;

public class CarteSimulable {
    public static Carte carteSim;
    private static int squareSide;
    private static int minX;
    private static int minY;

    /**
     *
     * @param
     * @return
     */
    public static Carte getCarteSim() {return CarteSimulable.carteSim;}

    /**
     *
     * @param
     * @return
     */
    public static int getSquareSide() {
        return CarteSimulable.squareSide;
    }

    /**
     *
     * @param
     * @return
     */
    public static void setSquareSide(int squareSide) {
        CarteSimulable.squareSide = squareSide;
    }

    public static int getMinX() {
        return CarteSimulable.minX;
    }
    public static void setMinX(int minX) {
        CarteSimulable.minX = minX;
    }

    public static int getMinY() {
        return CarteSimulable.minY;
    }
    public static void setMinY(int minY) {
        CarteSimulable.minY = minY;
    }

    /**
     *
     * @param
     * @return
     */
    public static void draw(GUISimulator gui, Carte carte) {
        carteSim = carte;

        CaseSimulable currCase;
        for(int i = 0; i < carte.getNbLignes(); i++) {
            for(int j = 0; j < carte.getNbColonnes(); j++) {
                currCase = new CaseSimulable(gui, carte.getCase(i, j));
                currCase.draw();
            }
        }
    }
}
