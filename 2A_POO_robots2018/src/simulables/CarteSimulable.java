package simulables;

import gui.GUISimulator;
import RepresentationDonnees.Carte;

public class CarteSimulable {
    public static Carte carteSim;
    private static int squareSide;
    private static int minX;
    private static int minY;

    public static Carte getCarteSim() {return CarteSimulable.carteSim;}

    public static void setSquareSide(int l) {
        CarteSimulable.squareSide = l;
    }
    public static int getSquareSide() {
        return CarteSimulable.squareSide;
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
