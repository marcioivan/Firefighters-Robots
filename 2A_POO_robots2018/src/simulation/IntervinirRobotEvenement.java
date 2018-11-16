package simulation;

import simulables.IncendieSimulable;
import simulables.RobotSimulable;

public class IntervinirRobotEvenement extends Evenement {
    private RobotSimulable robotSimulable;
    private IncendieSimulable incendieSimulable;

    public IntervinirRobotEvenement(RobotSimulable r, IncendieSimulable i, long date) {
        super(date);
        robotSimulable = r;
        incendieSimulable = i;
    }


    @Override
    public void execute() {
        try {
            while(robotSimulable.getRobot().getVolume() > 0 && ! incendieSimulable.isEstinguished()) {
//                System.out.println("Robot Volume: " + robotSimulable.getRobot().getVolume());
                int lit = robotSimulable.deverser();
                if (incendieSimulable != null) {
                    incendieSimulable.decroitre(lit);
//                    System.out.println("APAGA: " + incendieSimulable.getIncendie().getIntensite());
                }
            }
        } catch (ArithmeticException e) {
            System.out.println("Pas d'eau!");
        }

    }
}
