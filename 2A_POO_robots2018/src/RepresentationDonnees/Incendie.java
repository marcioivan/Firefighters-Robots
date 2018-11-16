package RepresentationDonnees;

public class Incendie {
    private Case position;
    private int intensite;

    public Incendie(Case pos, int intens) {
        position = pos;
        intensite = intens;
    }

    public Case getPosition() {
        return position;
    }

    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int newIntensite) {
        intensite = newIntensite;
    }

}
