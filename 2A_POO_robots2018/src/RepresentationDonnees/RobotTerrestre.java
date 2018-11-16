package RepresentationDonnees;

public abstract class RobotTerrestre extends Robot{

    public RobotTerrestre(Case pos, int vit) {
        super.setPosition(pos);
        super.volume = 0;
        super.vitesse = vit;
    }

    public RobotTerrestre(Case pos, int vit, int volume) {
        super.setPosition(pos);
        super.vitesse = vit;
        super.volume = volume;
    }

}
