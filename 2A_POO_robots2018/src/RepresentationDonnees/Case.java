package RepresentationDonnees;

public class Case {
	private int ligne, colonne;
	private NatureTerrain nature;

	public Case(int l, int c, NatureTerrain nt) {
		ligne = l;
		colonne = c;
		nature = nt;
	}

	public Case(int l, int c) {
		ligne = l;
		colonne = c;
		nature = NatureTerrain.TERRAIN_LIBRE;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;

		if (!(obj instanceof Case)) {
			return false;
		}

		Case tile = (Case) obj;
		return ligne == tile.getLigne() && colonne == tile.getColonne() && nature.equals(tile.getNature());
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public NatureTerrain getNature() {
		return nature;
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public Direction getDirectionVoisin(Case voisin) {
		int difLigne = this.ligne - voisin.getLigne();
		int difColonne = this.colonne - voisin.getColonne();

		if(difLigne == 1)
			return Direction.NORD;

		if(difLigne == -1)
			return Direction.SUD;

		if(difColonne == 1)
			return Direction.OUEST;

		if(difColonne == -1)
			return Direction.EST;

		return null;
	}
}
