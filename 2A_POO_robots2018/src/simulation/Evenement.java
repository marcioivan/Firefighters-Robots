package simulation;

public abstract class Evenement {
    private long date;

    public Evenement(long d) { date = d; }

    public long getDate() { return date; }

    public abstract void execute();

}
