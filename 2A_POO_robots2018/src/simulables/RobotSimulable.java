package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import representation_donnees.*;

public class RobotSimulable implements Simulable {
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile;
    private Robot robot;

    public RobotSimulable(GUISimulator gui, Robot r) {
        this.gui = gui;
        gui.setSimulable(this);
        this.robot = r;

        try {

            this.x = robot.getPosition().getColonne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge
            this.y = robot.getPosition().getLigne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
        }

        if(robot instanceof RobotDrone) imagefile = "textures/drone_texture.gif";
        else if(robot instanceof RobotChenilles) imagefile = "textures/chenilles_texture.gif";
        else if(robot instanceof RobotRoues) imagefile = "textures/roues_texture.gif";
        else if(robot instanceof RobotPattes) imagefile = "textures/pattes_texture.gif";

    }

    @Override
    public void next() {

    }

    @Override
    public void restart() {

    }

    public void draw() {
        //System.out.println("printing robot at (" + robot.getPosition().getLigne() + ", " + robot.getPosition().getColonne() + ")" );
        if(imagefile != null)
            gui.addGraphicalElement(new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null));
    }
}
