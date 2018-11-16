package representation_donnees;

public class Incendie {
    private Case position;
    private int intensite;

    public Incendie(Case pos, int intens) {
        position = pos;
        intensite = intens;
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
    public void setIntensite(int new_intensite) { intensite = new_intensite; }

}
