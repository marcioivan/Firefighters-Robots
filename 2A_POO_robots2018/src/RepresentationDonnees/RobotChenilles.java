package RepresentationDonnees;

public class RobotChenilles extends RobotTerrestre {

    public RobotChenilles(Case pos, int vit) {
        super(pos, vit);
    }

    public RobotChenilles(Case pos) {
        super(pos, 60);
    }

    @Override
    public double getVitesse(NatureTerrain nt) {
        int vitesse = 0;

        switch (nt) {
            case EAU:
            case ROCHE: vitesse = 0; break;
            case FORET: vitesse = super.vitesse / 2; break;
            case HABITAT:
            case TERRAIN_LIBRE: vitesse = super.vitesse; break;
        }

        return vitesse;
    }

    @Override
    public void deverserEau(int vol) throws ArithmeticException{
        if(super.volume - vol > 0)
            super.volume -= vol;
        else {
            super.volume = 0;
            throw new ArithmeticException();
        }
    }

    @Override
    public void remplirReservoir() {
        super.volume = 2000;
    }
}
