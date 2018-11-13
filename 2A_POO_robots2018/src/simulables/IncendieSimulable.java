package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import representation_donnees.Incendie;

public class IncendieSimulable{
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile = "textures/incendie_texture.gif";
    private Incendie incendie;
    private ImageElement image;


    IncendieSimulable(GUISimulator gui, Incendie i) {
        this.gui = gui;
        this.incendie = i;

        try {

            this.x = incendie.getPosition().getColonne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge
            this.y = incendie.getPosition().getLigne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
        }
    }

    public int getColonne() {
        return (x - (int) CarteSimulable.getSquareSide()) / (int) CarteSimulable.getSquareSide();
    }

    public int getLigne() {
        return (y - (int) CarteSimulable.getSquareSide()) / (int) CarteSimulable.getSquareSide();
    }

    public void decroitre(int lit) {
        if(incendie.getIntensite() - lit > 0) {
            //System.out.println("Feu intensite: " + incendie.getIntensite());
            incendie.setIntensite(incendie.getIntensite() - lit);
        }
        else {
            incendie.setIntensite(0);
        }
    }

    public void eteindre() {
        System.out.println("Le feu a été éteint");
        image.translate(1000000000, 1000000000); //delete
    }

    void draw() {
        image = new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null);
        gui.addGraphicalElement(image);
    }

}
