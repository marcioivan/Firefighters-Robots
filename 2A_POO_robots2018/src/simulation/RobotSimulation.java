package simulation;

import representation_donnees.Carte;
import representation_donnees.Case;
import representation_donnees.Robot;

public class RobotSimulation {
    private Robot robot;
    private Carte carte;

    public RobotSimulation(Robot r, Carte c) {
        robot = r;
        carte = c;
    }

    private double timeToCross(Case square) { // in seconds
        return (robot.getVitesse(square.getNature()) * 1000)  / (carte.getTailleCases() * 3600);
    }

    private void getShortestWay(Case dst) {}

    public void moveTo(Case dst) {
        getShortestWay(dst);
        //Create events
    }
}
