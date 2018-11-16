import gui.GUISimulator;
import io.LecteurDonnees;
import RepresentationDonnees.Direction;
import RepresentationDonnees.DonneesSimulation;
import simulables.*;
import simulation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestSimulateur {

    public static void main(String[] args) {
        DonneesSimulation simData;

        if (args.length < 1) {
            System.out.println("Syntaxe: java TestImpression <nomDeFichier>");
            System.exit(1);
        }

        try {

            simData = LecteurDonnees.creeDonnees(args[0]);

            //Start simulation
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            Simulateur simulateur = new Simulateur(gui, simData);


            // Run events
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(RobotsSimulable.getRobotSimulable(0), Direction.NORD, 1));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(RobotsSimulable.getRobotSimulable(0), Direction.NORD, 2));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(RobotsSimulable.getRobotSimulable(0), Direction.NORD, 3));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(RobotsSimulable.getRobotSimulable(0), Direction.NORD, 4));

            RobotSimulable robot_2 = RobotsSimulable.getRobotSimulable(1);
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robot_2, Direction.NORD, 1));
            IncendieSimulable incendie = IncendiesSimulable.getIncendieAt(5, 5);
            simulateur.ajouteEvenement(new IntervinirRobotEvenement(robot_2, incendie, 2));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robot_2, Direction.OUEST, 3));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robot_2, Direction.OUEST, 4));
            simulateur.ajouteEvenement(new DebutRemplirRobotEvenement(5));
            simulateur.ajouteEvenement(new FinRemplirRobotEvenement(robot_2, 35));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robot_2, Direction.EST, 36));
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robot_2, Direction.EST, 37));
            simulateur.ajouteEvenement(new IntervinirRobotEvenement(robot_2, incendie, 38));
            simulateur.ajouteEvenement(new EteindreIncendieEvenement(incendie, 38));

            // Little test for shortest path
//            RobotSimulable robot_3 = RobotsSimulable.getRobotSimulable(2);
//
//            RobotSimulation simu = new RobotSimulation(robot_3, simData.getCarte(), simulateur);
//            simu.moveTo(simData.getCarte().getCase(2, 1), 40);

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }
}
