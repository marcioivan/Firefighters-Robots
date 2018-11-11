package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import representation_donnees.Incendie;

public class IncendieSimulable{
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile = "textures/incendie_texture.gif";
    private Incendie incendie;


    public IncendieSimulable(GUISimulator gui, Incendie i) {
        this.gui = gui;
        this.incendie = i;

        try {

            this.x = incendie.getPosition().getColonne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge
            this.y = incendie.getPosition().getLigne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
        }
    }

    public void draw() {
        gui.addGraphicalElement(new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null));
    }

}
