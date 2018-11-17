package simulation;

import simulables.IncendieSimulable;

public class EteindreIncendieEvenement extends Evenement {
    private IncendieSimulable incendieSimulable;
    private ChefPompierElementaire chef;


    public EteindreIncendieEvenement(IncendieSimulable i, long date) {
        super(date);
        incendieSimulable = i;
    }

    public EteindreIncendieEvenement(IncendieSimulable i, ChefPompierElementaire c, long date) {
        super(date);
        incendieSimulable = i;
        chef = c;
    }

    @Override
    public void execute() {
        if(incendieSimulable.isEstinguished()) {
            incendieSimulable.eteindre();
            if(chef != null)
                chef.signalFireEstinguished(incendieSimulable);
        }
    }
}
