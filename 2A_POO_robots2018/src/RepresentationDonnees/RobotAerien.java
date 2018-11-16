package RepresentationDonnees;

public abstract class RobotAerien extends Robot{

    public RobotAerien(Case pos, int vit) {
        super.setPosition(pos);
        super.volume = 0;
        super.vitesse = vit;
    }

}
