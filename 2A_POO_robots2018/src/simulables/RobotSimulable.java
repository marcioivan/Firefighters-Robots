package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import representation_donnees.*;

import java.util.ArrayList;
import java.util.Iterator;

public class RobotSimulable{
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile;
    private Robot robot;
    private ImageElement image;

     RobotSimulable(GUISimulator gui, Robot r) {
        this.gui = gui;
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

    /**
     *
     * @param
     * @return
     */
    public Robot getRobot() { return this.robot;}

    /**
     *
     * @param
     * @return
     */
    public void moveDirection (Direction d) {
        Case new_pos = robot.getPosition();
         try {
             new_pos = CarteSimulable.getCarteSim().getVoisin(robot.getPosition(), d);
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("Voisin ne existe pas");
         }
         System.out.println("new pos (" + new_pos.getLigne() + ", " + new_pos.getColonne() + ")");
         robot.setPosition(new_pos);

         boolean can_move = true;
        try {

            this.x = robot.getPosition().getColonne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge
            this.y = robot.getPosition().getLigne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
            can_move = false;
        }

        if(can_move)
            translade(d);
     }

    /**
     *
     * @param
     * @return
     */
     public int deverser() {
         if(robot instanceof RobotDrone) { robot.deverserEau(0); return 10000; }
         else if(robot instanceof RobotChenilles) { robot.deverserEau(100); return 100; }
         else if(robot instanceof RobotRoues) { robot.deverserEau(100); return 100; }
         else if(robot instanceof RobotPattes) { robot.deverserEau(10); return 10; }

         return 0;
     }

    /**
     *
     * @param
     * @return
     */
     public void remplir() {
         robot.remplirReservoir();
     }

    /**
     *
     * @param
     * @return
     */
    void draw() {
        //System.out.println("printing robot at (" + robot.getPosition().getLigne() + ", " + robot.getPosition().getColonne() + ")" );
        if(imagefile != null) {
            image = new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null);
            gui.addGraphicalElement(image);
        }
    }

    /**
     *
     * @param
     * @return
     */
    private void translade(Direction d) {
         int dx = 0, dy = 0;

         switch (d) {
             case OUEST: dx = - (int) CarteSimulable.getSquareSide(); break;
             case NORD: dy = - (int) CarteSimulable.getSquareSide(); break;
             case SUD: dy = (int) CarteSimulable.getSquareSide(); break;
             case EST: dx = (int) CarteSimulable.getSquareSide(); break;
         }

         image.translate(dx, dy);
    }
}
