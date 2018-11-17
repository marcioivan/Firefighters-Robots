package RepresentationDonnees;

public class RobotPattes extends RobotTerrestre{

    public RobotPattes(Case pos) {
        super(pos, 30, 10);
    }

    @Override
    public double getVitesse(NatureTerrain nt) {
        int vitesse = 0;

        switch (nt) {
            case EAU: vitesse = 0; break;
            case ROCHE: vitesse = 10; break;
            case FORET:
            case HABITAT:
            case TERRAIN_LIBRE: vitesse = super.vitesse; break;
        }

        return vitesse;
    }

    @Override
    public void deverserEau(int vol) {
        //rien
    }

    @Override
    public void remplirReservoir() {
        //rien
    }
}
