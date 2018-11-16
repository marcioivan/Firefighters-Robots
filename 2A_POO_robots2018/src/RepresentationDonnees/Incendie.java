package RepresentationDonnees;

public class Incendie {
    private Case position;
    private int intensite;

    public Incendie(Case position, int intensite) {
        this.position = position;
        this.intensite = intensite;
    }

    /**
     *
     * @param
     * @return
     */
    public Case getPosition() {
        return position;
    }

    /**
     *
     * @param
     * @return
     */
    public int getIntensite() {
        return intensite;
    }

    /**
     *
     * @param
     * @return
     */
    public void setIntensite(int newIntensite) {
        intensite = newIntensite;
    }

}
