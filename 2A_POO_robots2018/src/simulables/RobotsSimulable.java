package simulables;

import gui.GUISimulator;
import representation_donnees.Robot;

import java.util.ArrayList;

public class RobotsSimulable {
    private static ArrayList<RobotSimulable> robots_simulables;

    public static void initRobotsSimulablesList(int size) {
        robots_simulables = new ArrayList<RobotSimulable>(size);
    }

    public static ArrayList<RobotSimulable> getRobotsList() { return robots_simulables; }

    public static RobotSimulable getRobotSimulable(int index) { return robots_simulables.get(index); }

    public static void drawRobots(GUISimulator gui, ArrayList<Robot> robots_list) {
        RobotSimulable curr_robot;

        for(int i = 0; i < robots_list.size(); i++) {
            curr_robot = new RobotSimulable(gui, robots_list.get(i));
            curr_robot.draw();
            robots_simulables.add(curr_robot);
        }
    }
}
