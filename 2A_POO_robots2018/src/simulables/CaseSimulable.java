package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import RepresentationDonnees.Case;

public class CaseSimulable{
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile;
    private Case tile;

    public CaseSimulable(GUISimulator gui, Case pos) {
        this.gui = gui;
        this.tile = pos;

        try {

            this.x = CarteSimulable.getMinX() + tile.getColonne() * CarteSimulable.getSquareSide();
            this.y = CarteSimulable.getMinY() + tile.getLigne() * CarteSimulable.getSquareSide();

        } catch (NullPointerException e) {
            System.out.println("Case invalid");
        }

        switch(tile.getNature()) {
            case TERRAIN_LIBRE: this.imagefile = "textures/terrain_libre_texture.jpg"; break;
            case HABITAT: this.imagefile = "textures/habitat_texture.jpg"; break;
            case FORET: this.imagefile = "textures/foret_texture.jpg"; break;
            case EAU: this.imagefile = "textures/eau_texture.gif"; break;
            case ROCHE: this.imagefile = "textures/roche_texture.jpg"; break;
        }
    }

    /**
     *
     * @param
     * @return
     */
    public void draw() {
        //System.out.println("printing (" + tile.getLigne() + ", " + tile.getColonne() + ")" );
        gui.addGraphicalElement(new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null));
    }
}
