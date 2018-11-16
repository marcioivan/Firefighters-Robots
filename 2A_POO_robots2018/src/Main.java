import gui.GUISimulator;
import io.LecteurDonnees;
import representation_donnees.DonneesSimulation;
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
        DonneesSimulation sim_data;

        if (args.length < 1) {
            System.out.println("Syntaxe: java TestImpression <nomDeFichier>");
            System.exit(1);
        }

        try {

            sim_data = LecteurDonnees.creeDonnees(args[0]);

            //Start simulation
            GUISimulator gui = new GUISimulator(600, 600, Color.BLACK);
            double square_side_bulk = (double) (600 * 600) / (sim_data.getCarte().getNbLignes() * sim_data.getCarte().getNbColonnes()); //area per square
            square_side_bulk = Math.ceil(Math.sqrt(square_side_bulk)); //side per square

            CarteSimulable.setSquareSide(square_side_bulk);
            CarteSimulable.draw(gui, sim_data.getCarte());

            RobotsSimulable.initRobotsSimulablesList(sim_data.getRobotsList().size());
            RobotsSimulable.drawRobots(gui, sim_data.getRobotsList());

            IncendiesSimulable.initIncendiesSimulablesList(sim_data.getIncendiesList().size());
            IncendiesSimulable.drawIncendies(gui, sim_data.getIncendiesList());

            Simulateur simulateur = new Simulateur(gui);

            ChefPompierElementaire chef = new ChefPompierElementaire(IncendiesSimulable.getIncendiesList());
            for (RobotSimulable robotSimulable : RobotsSimulable.getRobotsList()) {
                chef.introduce(new RobotSimulation(robotSimulable, sim_data.getCarte(), simulateur));
            }

            chef.chefier();


        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }
}
