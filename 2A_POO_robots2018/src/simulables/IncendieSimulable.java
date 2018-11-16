package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import RepresentationDonnees.Incendie;

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

            this.x = CarteSimulable.getMinX() + incendie.getPosition().getColonne() * CarteSimulable.getSquareSide();
            this.y = CarteSimulable.getMinY() + incendie.getPosition().getLigne() * CarteSimulable.getSquareSide();

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
        }
    }

    /**
     *
     * @param
     * @return
     */
    public int getColonne() {
        return(x - CarteSimulable.getMinX()) / CarteSimulable.getSquareSide();
    }

    /**
     *
     * @param
     * @return
     */
    public int getLigne() {
        return (y - CarteSimulable.getMinY()) / CarteSimulable.getSquareSide();
    }

    /**
     *
     * @param
     * @return
     */
    public Incendie getIncendie() {
        return incendie;
    }

    /**
     *
     * @param
     * @return
     */
    public void decroitre(int lit) {
        if(incendie.getIntensite() - lit > 0) {
            incendie.setIntensite(incendie.getIntensite() - lit);
        }
        else {
            incendie.setIntensite(0);
        }
    }

    /**
     *
     * @param
     * @return
     */
    public boolean isEstinguished() {
        return incendie.getIntensite() == 0;
    }

    /**
     *
     * @param
     * @return
     */
    public void eteindre() {
        System.out.println("Le feu a été éteint");
        image.translate(1000000000, 1000000000); //delete
    }

    /**
     *
     * @param
     * @return
     */
    void draw() {
        image = new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null);
        gui.addGraphicalElement(image);
    }

}
