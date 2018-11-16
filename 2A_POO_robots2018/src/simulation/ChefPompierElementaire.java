package simulation;

import simulables.IncendieSimulable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ChefPompierElementaire {
    private ArrayList<IncendieSimulable> incendies;
    private LinkedList<RobotSimulation> robotsLibres;
    private HashMap<IncendieSimulable, RobotSimulation> tasks;

    public ChefPompierElementaire(ArrayList<IncendieSimulable> incendies) {
        this.incendies = new ArrayList<>(incendies);
        this.robotsLibres = new LinkedList<>();
        this.tasks = new HashMap<>(incendies.size());
    }


    /**
     *
     * @param
     * @return
     */
    public void chefier() {
        while(!incendies.isEmpty()) {
            RobotSimulation robot;

            for (IncendieSimulable incendie : incendies) {
                if (!tasks.containsKey(incendie)) {
                    if (!robotsLibres.isEmpty()) {
                        robot = robotsLibres.poll();
                        robot.intervinir(incendie);
                        tasks.put(incendie, robot);
                    }
                }
            }
        }
    }

    /**
     *
     * @param
     * @return
     */
    public void introduce(RobotSimulation newRobot) {
        robotsLibres.add(newRobot);
        newRobot.setChef(this);
    }

    /**
     *
     * @param
     * @return
     */
    public void signalFree(RobotSimulation robot) {
        robotsLibres.add(robot);
    }

    /**
     *
     * @param
     * @return
     */
    public void signalOccupied(RobotSimulation robot) {
        robotsLibres.remove(robot);
    }

    /**
     *
     * @param
     * @return
     */
    public void signalFireEstinguished(IncendieSimulable incendie) {
        incendies.remove(incendie);
        tasks.remove(incendie);
    }
}
