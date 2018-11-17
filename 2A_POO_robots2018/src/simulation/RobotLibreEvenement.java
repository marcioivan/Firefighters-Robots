package simulation;

public class RobotLibreEvenement extends Evenement{
    private RobotSimulation robot;
    private ChefPompierElementaire chef;

    public RobotLibreEvenement(RobotSimulation r, ChefPompierElementaire c, long date) {
        super(date);
        robot = r;
        chef = c;
    }

    @Override
    public void execute() {
        System.out.println("[" + robot.toString() +"] I'm free");
        chef.signalFree(robot);
    }
}
