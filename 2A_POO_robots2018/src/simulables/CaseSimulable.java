package simulables;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Simulable;
import representation_donnees.Case;
import representation_donnees.NatureTerrain;

import java.awt.*;

public class CaseSimulable implements Simulable {
    private GUISimulator gui;
    private int x;
    private int y;
    private String imagefile;
    private Case tile;

    public CaseSimulable(GUISimulator gui, Case pos) {
        this.gui = gui;
        gui.setSimulable(this);
        this.tile = pos;

        try {

            this.x = tile.getColonne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge
            this.y = tile.getLigne() * (int) CarteSimulable.getSquareSide() + (int) CarteSimulable.getSquareSide(); // 1 square extra for edge

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


    @Override
    public void next() {

    }

    @Override
    public void restart() {

    }

    public void draw() {
        //System.out.println("printing (" + tile.getLigne() + ", " + tile.getColonne() + ")" );
        gui.addGraphicalElement(new ImageElement(x, y, imagefile, (int) CarteSimulable.getSquareSide(), (int) CarteSimulable.getSquareSide(), null));
    }
}
