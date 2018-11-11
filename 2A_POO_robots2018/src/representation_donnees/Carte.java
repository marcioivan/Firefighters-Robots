package representation_donnees;

public class Carte {
	private int taille;
	private Case[][] carte_matrix;
	private int n_lin, n_col;

	public Carte(int nbLignes, int nbColonnes, int tailleCases) {
		carte_matrix = new Case[nbLignes][nbColonnes];
		n_lin = nbLignes;
		n_col = nbColonnes;
		taille = tailleCases;
	}
	public int getNbLignes() {
		return n_lin;
	}
	public int getNbColonnes() {
		return n_col;
	}
	public int getTailleCases() {
		return taille;
	}
	public void insertCase(int ligne, int colonne, Case c) {
		carte_matrix[ligne][colonne] = c;
	}
	public Case getCase(int lig, int col) {
		return carte_matrix[lig][col];
	}
	//public boolean voisinExiste(Case src, Direction dir){}
	//public Case getVoisin(Case src, Direction dir){}
}
