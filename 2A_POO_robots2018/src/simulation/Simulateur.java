package simulation;

import gui.GUISimulator;
import gui.Simulable;

import java.util.LinkedList;

public class Simulateur implements Simulable {
    private long dateSimulation;
    private LinkedList<Evenement> evenements;

    public Simulateur(GUISimulator gui) {
        dateSimulation = 0;
        evenements = new LinkedList<>();
        gui.setSimulable(this);
    }

    public void ajouteEvenement(Evenement e) {
        evenements.add(e);
    }

    private void incrementeDate() { dateSimulation++; }

    private boolean simulationTerminee() {
        return evenements.peekFirst() == null;
    }

    @Override
    public void next() {
        this.incrementeDate();

        while(!simulationTerminee() && evenements.peek().getDate() == dateSimulation) {
            evenements.poll().execute();
        }
    }

    @Override
    public void restart() {
        dateSimulation = 0;
        evenements = new LinkedList<>();
    }
}
