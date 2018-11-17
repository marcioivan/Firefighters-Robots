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
        //while(!incendies.isEmpty()) {
            RobotSimulation robot;

            for (IncendieSimulable incendie : incendies) {
                if(!robotsLibres.isEmpty()) {
                    if (!tasks.containsKey(incendie)) {
                        if (!tasks.containsValue(robotsLibres.peekFirst())) {
                            robot = robotsLibres.poll();
                            robot.intervinir(incendie);
                            System.out.println("[Chef Pompier] Sending fire (" + incendie.getLigne() + ", " + incendie.getColonne() + ") to robot " + robot.toString());
                            tasks.put(incendie, robot);
                        }
                    } else if (robotsLibres.contains(tasks.get(incendie))) {
                        robot = robotsLibres.poll();
                        robot.intervinir(incendie);
                        System.out.println("[Chef Pompier] Sending fire (" + incendie.getLigne() + ", " + incendie.getColonne() + ") to robot " + robot.toString());
                    }
                }
            }
        //}
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
        chefier();
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
        System.out.println("[Chef Pompier] Fire at (" + incendie.getLigne() + ", " + incendie.getColonne() + ") out.");
        incendies.remove(incendie);
        tasks.remove(incendie);
    }
}
