package simulables;

import gui.GUISimulator;
import representation_donnees.Carte;

public class CarteSimulable {
    private static double square_side;

    public static void setSquareSide(double l) {
        CarteSimulable.square_side = l;
    }

    public static double getSquareSide() {
        return CarteSimulable.square_side;
    }

    public static void draw(GUISimulator gui, Carte carte) {

        CaseSimulable curr_case;
        for(int i = 0; i < carte.getNbLignes(); i++) {
            for(int j = 0; j < carte.getNbColonnes(); j++) {
                curr_case = new CaseSimulable(gui, carte.getCase(i, j));
                curr_case.draw();
            }
        }
    }
}
