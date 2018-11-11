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

	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public NatureTerrain getNature() {
		return nature;
	}
}
