import gui.GUISimulator;
import io.LecteurDonnees;
import RepresentationDonnees.DonneesSimulation;
import simulables.CarteSimulable;
import simulables.IncendiesSimulable;
import simulables.RobotsSimulable;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestImpression {

    public static void main(String[] args) {
        DonneesSimulation simData;

        if (args.length < 1) {
            System.out.println("Syntaxe: java TestImpression <nomDeFichier>");
            System.exit(1);
        }

        try {

            simData = LecteurDonnees.creeDonnees(args[0]);

            //Start simulation
            GUISimulator gui = new GUISimulator(600, 600, Color.BLACK);

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }

}
