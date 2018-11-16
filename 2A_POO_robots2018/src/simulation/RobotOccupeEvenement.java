package simulation;

public class RobotOccupeEvenement extends Evenement {
    private RobotSimulation robot;
    private ChefPompierElementaire chef;

    public RobotOccupeEvenement(RobotSimulation r, ChefPompierElementaire c, long date) {
        super(date);
        robot = r;
        chef = c;
    }

    @Override
    public void execute() {
        chef.signalOccupied(robot);
    }
}
