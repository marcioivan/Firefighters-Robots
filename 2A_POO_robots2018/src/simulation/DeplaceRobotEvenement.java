package simulation;

import RepresentationDonnees.Direction;
import simulables.RobotSimulable;

public class DeplaceRobotEvenement extends Evenement {
    private RobotSimulable robotSimulable;
    private Direction direction;

    public DeplaceRobotEvenement(RobotSimulable r, Direction dir, long date) {
        super(date);
        robotSimulable = r;
        direction = dir;
    }

    @Override
    public void execute() {
        robotSimulable.moveDirection(direction);
    }
}
