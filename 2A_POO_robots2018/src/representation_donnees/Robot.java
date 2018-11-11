package representation_donnees;

public abstract class Robot {
	private Case position;
	protected int volume;
	protected int vitesse;

	public Case getPosition(){
		return position;
	}
	public void setPosition(Case c){
		position = c;
	}

	public abstract double getVitesse(NatureTerrain nt);
	public abstract void deverserEau(int vol);
	public abstract void remplirReservoir();
}
