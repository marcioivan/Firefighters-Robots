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


    public void chefier() {
        while(!incendies.isEmpty()) {
            RobotSimulation aux;

            for (IncendieSimulable incendie : incendies) {
                if (!tasks.containsKey(incendie)) {
                    if (!robotsLibres.isEmpty()) {
                        aux = robotsLibres.poll();
                        aux.intervinir(incendie);
                        tasks.put(incendie, aux);
                    }
                } else if (robotsLibres.contains(tasks.get(incendie))) {
                    aux = tasks.get(incendie);
                    aux.intervinir(incendie);
                }
            }
        }
    }

    public void introduce(RobotSimulation newRobot) {
        robotsLibres.add(newRobot);
        newRobot.setChef(this);
    }

    public void signalFree(RobotSimulation robot) {
        robotsLibres.add(robot);
    }

    public void signalOccupied(RobotSimulation robot) {
        robotsLibres.remove(robot);
    }

    public void signalFireEstinguished(IncendieSimulable incendie) {
        incendies.remove(incendie);
        tasks.remove(incendie);
    }
}
