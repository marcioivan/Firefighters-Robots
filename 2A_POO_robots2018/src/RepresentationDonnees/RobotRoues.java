package RepresentationDonnees;

public class RobotRoues extends RobotTerrestre {

    public RobotRoues(Case pos, int vit) {
        super(pos, vit);
    }

    public RobotRoues(Case pos) {
        super(pos, 80);
    }

    @Override
    public double getVitesse(NatureTerrain nt) {
        if(nt == NatureTerrain.HABITAT || nt == NatureTerrain.TERRAIN_LIBRE)
            return super.vitesse;
        else
            return 0;
    }

    @Override
    public void deverserEau(int vol) throws ArithmeticException {
        if(super.volume - vol > 0)
            super.volume -= vol;
        else {
            super.volume = 0;
            throw new ArithmeticException();
        }
    }

    @Override
    public void remplirReservoir() {
        super.volume = 5000;
    }
}
