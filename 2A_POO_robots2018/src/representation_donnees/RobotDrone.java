package representation_donnees;

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
        //rien pour le moment
    }

    @Override
    public void remplirReservoir() {
        //rien pour le moment
    }
}
