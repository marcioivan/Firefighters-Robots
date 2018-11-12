package simulation;

import gui.GUISimulator;
import gui.Simulable;

import java.util.Comparator;
import java.util.PriorityQueue;

class EvenementComparator implements Comparator<Evenement> {
    @Override
    public int compare(Evenement e1, Evenement e2) {
        if(e1.getDate() < e2.getDate())
            return -1;
        if (e1.getDate() > e2.getDate())
            return 1;
        return 0;
    }
}

public class Simulateur implements Simulable {
    private long dateSimulation;
    private PriorityQueue<Evenement> evenements;

    public Simulateur(GUISimulator gui) {
        dateSimulation = 0;
        evenements = new PriorityQueue<>(10, new EvenementComparator());
        gui.setSimulable(this);
    }

    public void ajouteEvenement(Evenement e) {
        evenements.add(e);
    }

    private void incrementeDate() { dateSimulation++; }

    private boolean simulationTerminee() {
        return evenements.size() == 0;
    }

    @Override
    public void next() {
        this.incrementeDate();

        while(!simulationTerminee() && evenements.peek().getDate() <= dateSimulation) {
            evenements.poll().execute();
        }
    }

    @Override
    public void restart() {
        dateSimulation = 0;
        evenements = new PriorityQueue<>(10, new EvenementComparator());
    }
}
