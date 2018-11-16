package simulation;

public abstract class Evenement {
    private long date;

    public Evenement(long d) { date = d; }

    /**
     *
     * @param
     * @return
     */
    public long getDate() { return date; }

    /**
     *
     * @param
     * @return
     */
    public abstract void execute();

}
