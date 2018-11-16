package simulables;

import gui.GUISimulator;
import RepresentationDonnees.Incendie;

import java.util.ArrayList;

public class IncendiesSimulable {
    private static ArrayList<IncendieSimulable> incendiesSimulables;

    public static void initIncendiesSimulablesList(int size) {
        incendiesSimulables = new ArrayList<>(size);
    }

    public static void clearIncendiesSimulablesList() {
        if(incendiesSimulables != null) {
            incendiesSimulables.clear();
        }
    }

    public static  ArrayList<IncendieSimulable> getIncendiesList() { return incendiesSimulables; }

    public static IncendieSimulable getIncendieAt(int lig, int col) {
        for (IncendieSimulable incendieSimulable : incendiesSimulables) {
            if (incendieSimulable.getLigne() == lig && incendieSimulable.getColonne() == col)
                return incendieSimulable;
        }
        return null;
    }

    public static void drawIncendies(GUISimulator gui, ArrayList<Incendie> incendiesList) {
        IncendieSimulable currIncendie;

        for(int i = 0; i < incendiesList.size(); i++) {
            currIncendie = new IncendieSimulable(gui, incendiesList.get(i));
            currIncendie.draw();
            incendiesSimulables.add(currIncendie);
        }
    }

}
