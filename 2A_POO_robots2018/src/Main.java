import gui.GUISimulator;
import io.LecteurDonnees;
import RepresentationDonnees.DonneesSimulation;
import simulables.CarteSimulable;
import simulables.IncendiesSimulable;
import simulables.RobotSimulable;
import simulables.RobotsSimulable;
import simulation.ChefPompierElementaire;
import simulation.RobotSimulation;
import simulation.Simulateur;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Main {
    public static void main (String[] args) {
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

            // Little test for shortest path
            RobotSimulable robot_3 = RobotsSimulable.getRobotSimulable(2);

            RobotSimulation simu = new RobotSimulation(robot_3, simData.getCarte(), simulateur);
            simu.moveTo(simData.getCarte().getCase(2, 1), 1);

//            ChefPompierElementaire chef = new ChefPompierElementaire(IncendiesSimulable.getIncendiesList());
//            for (RobotSimulable robotSimulable : RobotsSimulable.getRobotsList()) {
//                chef.introduce(new RobotSimulation(robotSimulable, simData.getCarte(), simulateur));
//            }
//
//            chef.chefier();

        } catch (
                FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (
                DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }



    }
}
