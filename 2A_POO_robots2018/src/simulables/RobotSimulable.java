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

    public void moveDirection (Direction d) {
        Case new_pos = null;
         try {
             new_pos = CarteSimulable.getCarteSim().getVoisin(robot.getPosition(), d);
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("Voisin ne existe pas");
         }

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


    void draw() {
        //System.out.println("printing robot at (" + robot.getPosition().getLigne() + ", " + robot.getPosition().getColonne() + ")" );
        if(imagefile != null) {
            image = new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null);
            gui.addGraphicalElement(image);
        }
    }

    void translade(Direction d) {
         int dx = 0, dy = 0;

         switch (d) {
             case OUEST: dx = - (int) CarteSimulable.getSquareSide(); dx = 0; break;
             case NORD: dx = 0; dy = - (int) CarteSimulable.getSquareSide(); break;
             case SUD: dx = 0; dy = (int) CarteSimulable.getSquareSide(); break;
             case EST: dx = (int) CarteSimulable.getSquareSide(); dy = 0; break;
         }

         image.translate(dx, dy);
    }
}
