package RepresentationDonnees;

public class RobotDrone extends RobotAerien {

    public RobotDrone(Case pos, int vit) {
        super(pos, vit);
    }

    public RobotDrone(Case pos) {
        super(pos, 100);
    }

    @Override
    public double getVitesse(NatureTerrain nt) {
        return super.vitesse;
    }

    @Override
    public void deverserEau(int vol) {
        super.volume = 0;
    }

    @Override
    public void remplirReservoir() {
        super.volume = 10000;
    }
}
