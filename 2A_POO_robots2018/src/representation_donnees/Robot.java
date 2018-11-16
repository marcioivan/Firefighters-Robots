package representation_donnees;

public abstract class Robot {
	private Case position;
	int volume = 0;
	int vitesse;

	/**
	 *
	 * @param
	 * @return
	 */
	public Case getPosition(){
		return position;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public void setPosition(Case c){
		position = c;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public int getVolume() { return volume; }
	/**
	 *
	 * @param
	 * @return
	 */
	public abstract double getVitesse(NatureTerrain nt);
	/**
	 *
	 * @param
	 * @return
	 */
	public abstract void deverserEau(int vol);
	/**
	 *
	 * @param
	 * @return
	 */
	public abstract void remplirReservoir();
}
