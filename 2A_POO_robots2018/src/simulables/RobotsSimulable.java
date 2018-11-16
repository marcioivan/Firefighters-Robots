package simulables;

import gui.GUISimulator;
import RepresentationDonnees.Robot;

import java.util.ArrayList;

public class RobotsSimulable {
    private static ArrayList<RobotSimulable> robotsSimulables;

    /**
     *
     * @param
     * @return
     */
    public static void initRobotsSimulablesList(int size) {
        robotsSimulables = new ArrayList<RobotSimulable>(size);
    }

    public static void clearRobotsSimulablesList() {
        if(robotsSimulables != null) {
            robotsSimulables.clear();
        }
    }

    /**
     *
     * @param
     * @return
     */
    public static ArrayList<RobotSimulable> getRobotsList() {
        return robotsSimulables;
    }

    /**
     *
     * @param
     * @return
     */
    public static RobotSimulable getRobotSimulable(int index) { return robotsSimulables.get(index); }

    /**
     *
     * @param
     * @return
     */
    public static void drawRobots(GUISimulator gui, ArrayList<Robot> robotsList) {
        RobotSimulable currRobot;

        for(int i = 0; i < robotsList.size(); i++) {
            currRobot = new RobotSimulable(gui, robotsList.get(i));
            currRobot.draw();
            robotsSimulables.add(currRobot);
        }
    }
}
