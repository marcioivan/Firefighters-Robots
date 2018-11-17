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


            //Start simulation
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);

            Simulateur simulateur = new Simulateur(gui, args[0]);


    }
}
