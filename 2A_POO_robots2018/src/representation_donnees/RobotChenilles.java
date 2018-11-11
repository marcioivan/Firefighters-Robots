package representation_donnees;

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
    public void deverserEau(int vol) {
        //rien pour le moment
    }

    @Override
    public void remplirReservoir() {
        //rien pour le moment
    }
}
