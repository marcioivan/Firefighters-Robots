package representation_donnees;

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

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public NatureTerrain getNature() {
		return nature;
	}

	public Direction getDirectionVoisin(Case voisin) {
		int difLigne = this.ligne - voisin.getLigne();
		int difColonne = this.colonne - voisin.getColonne();

		if(difLigne > 0)
			return Direction.NORD;

		if(difLigne < 0)
			return Direction.SUD;

		if(difColonne < 0)
			return Direction.EST;

		return Direction.OUEST;
	}
}
