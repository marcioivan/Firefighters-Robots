package simulables;

import gui.GUISimulator;
import representation_donnees.Incendie;

import java.util.ArrayList;

public class IncendiesSimulable {
    private static ArrayList<IncendieSimulable> incendies_simulables;

    /**
     *
     * @param
     * @return
     */
    public static void initIncendiesSimulablesList(int size) {
        incendies_simulables = new ArrayList<IncendieSimulable>(size);
    }

    /**
     *
     * @param
     * @return
     */
    public static  ArrayList<IncendieSimulable> getIncendiesList() { return incendies_simulables; }

    /**
     *
     * @param
     * @return
     */
    public static IncendieSimulable getIncendieAt(int lig, int col) {
        for (IncendieSimulable incendie_simulable : incendies_simulables) {
            if (incendie_simulable.getLigne() == lig && incendie_simulable.getColonne() == col)
                return incendie_simulable;
        }
        return null;
    }

    /**
     *
     * @param
     * @return
     */
    public static void drawIncendies(GUISimulator gui, ArrayList<Incendie> incendies_list) {
        IncendieSimulable curr_incendie;

        for(int i = 0; i < incendies_list.size(); i++) {
            curr_incendie = new IncendieSimulable(gui, incendies_list.get(i));
            curr_incendie.draw();
            incendies_simulables.add(curr_incendie);
        }
    }

}
