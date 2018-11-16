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

	/**
	 *
	 * @param
	 * @return
	 */
	public int getNbLignes() {
		return n_lin;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public int getNbColonnes() {
		return n_col;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public int getTailleCases() {
		return taille;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public void insertCase(int ligne, int colonne, Case c) {
		carte_matrix[ligne][colonne] = c;
	}
	/**
	 *
	 * @param
	 * @return
	 */
	public Case getCase(int lig, int col) {
		return carte_matrix[lig][col];
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public boolean voisinExiste(Case src, Direction dir) {
		boolean existe = false;

		switch(dir) {
			case EST: if(src.getColonne() + 1 >= n_col) existe = false;
					  else existe = true;
					  break;
			case SUD: if(src.getLigne() + 1 >= n_lin) existe = false;
					  else existe = true;
					  break;
			case NORD: if(src.getLigne() - 1 < 0) existe = false;
					   else existe = true;
					   break;
			case OUEST: if(src.getColonne() - 1 < 0) existe = false;
						else existe = true;
						break;
		}

		return existe;
	}

	/**
	 *
	 * @param
	 * @return
	 */
	public Case getVoisin(Case src, Direction dir) throws ArrayIndexOutOfBoundsException {
		Case voisin = null;

		if(voisinExiste(src, dir)) {

			switch (dir) {
				case EST: voisin = this.getCase(src.getLigne(), src.getColonne() + 1); break;
				case SUD: voisin = this.getCase(src.getLigne() + 1, src.getColonne()); break;
				case NORD: voisin = this.getCase(src.getLigne() - 1, src.getColonne()); break;
				case OUEST: voisin = this.getCase(src.getLigne(), src.getColonne() - 1); break;
			}
		}
		else throw new ArrayIndexOutOfBoundsException();

		return voisin;
	}
}
