package simulation;

import RepresentationDonnees.DonneesSimulation;
import gui.GUISimulator;
import gui.Simulable;
import io.LecteurDonnees;
import simulables.CarteSimulable;
import simulables.IncendiesSimulable;
import simulables.RobotSimulable;
import simulables.RobotsSimulable;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

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
    private GUISimulator gui;
    private DonneesSimulation simData;
    private long dateSimulation;
    private PriorityQueue<Evenement> evenements;

    public Simulateur(GUISimulator gui, DonneesSimulation simData) {
        this.gui = gui;
        this.simData = simData;
        gui.setSimulable(this);
        dateSimulation = 0;
        evenements = new PriorityQueue<>(10, new EvenementComparator());
        initSimulation();
    }

    /**
     *
     * @param
     * @return
     */
    public void ajouteEvenement(Evenement e) {
        evenements.add(e);
    }

    /**
     *
     * @param
     * @return
     */
    public long getDateSimulation() {
        return dateSimulation;
    }

    /**
     *
     * @param
     * @return
     */
    private void incrementeDate() {
        dateSimulation++;
    }

    // Adjust simulation to best position on screen
    private void adjustScreen() {

        int panelHeight = gui.getPanelHeight();
        int panelWidth = gui.getPanelWidth();
        int nbLignes = simData.getCarte().getNbLignes();
        int nbColonnes = simData.getCarte().getNbColonnes();

        // Check panel for square or retangle
        if (panelWidth > panelHeight) {
            int borderSide = (panelWidth - panelHeight) / 2;

            // Check simulation for square or retangle
            if(nbLignes >= nbColonnes) {
                CarteSimulable.setSquareSide(panelHeight / nbLignes);
                CarteSimulable.setMinX(borderSide);
                CarteSimulable.setMinY(0);
            } else {
                CarteSimulable.setSquareSide(panelWidth / nbColonnes);
                CarteSimulable.setMinX(0);
                CarteSimulable.setMinY(borderSide);
            }
        } else {
            int borderSide = (gui.getPanelHeight() - gui.getPanelWidth()) / 2;

            // Check simulation for square or retangle
            if(nbLignes <= nbColonnes) {
                CarteSimulable.setSquareSide(gui.getPanelWidth() / nbColonnes);
                CarteSimulable.setMinX(0);
                CarteSimulable.setMinY(borderSide);
            } else {
                CarteSimulable.setSquareSide(gui.getPanelHeight() / nbLignes);
                CarteSimulable.setMinX(borderSide);
                CarteSimulable.setMinY(0);
            }
        }
    }

    private void initSimulation() {

        adjustScreen();

        CarteSimulable.draw(gui, simData.getCarte());

        RobotsSimulable.clearRobotsSimulablesList();
        RobotsSimulable.initRobotsSimulablesList(simData.getRobotsList().size());
        RobotsSimulable.drawRobots(gui, simData.getRobotsList());

        IncendiesSimulable.clearIncendiesSimulablesList();
        IncendiesSimulable.initIncendiesSimulablesList(simData.getIncendiesList().size());
        IncendiesSimulable.drawIncendies(gui, simData.getIncendiesList());
    }

    @Override
    public void next() {
        this.incrementeDate();

        while(evenements.size() > 0  && evenements.peek().getDate() <= dateSimulation) {
            evenements.poll().execute();
        }
    }

    @Override
    public void restart() {
        dateSimulation = 0;
        evenements.clear();
        gui.reset();
        initSimulation();
    }
}
