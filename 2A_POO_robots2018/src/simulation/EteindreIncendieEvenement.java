package simulation;

import simulables.IncendieSimulable;

public class EteindreIncendieEvenement extends Evenement {
    private IncendieSimulable incendieSimulable;


    public EteindreIncendieEvenement(IncendieSimulable i, long date) {
        super(date);
        incendieSimulable = i;
    }

    @Override
    public void execute() {
        incendieSimulable.eteindre();
    }
}
