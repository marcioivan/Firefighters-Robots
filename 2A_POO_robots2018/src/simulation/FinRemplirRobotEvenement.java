package simulation;

import simulables.RobotSimulable;

public class FinRemplirRobotEvenement extends Evenement{
    private RobotSimulable robotSimulable;

    public FinRemplirRobotEvenement(RobotSimulable r, long date) {
        super(date);
        robotSimulable = r;
    }


    @Override
    public void execute() {
        robotSimulable.remplir();
    }
}
