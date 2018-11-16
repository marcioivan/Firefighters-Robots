package RepresentationDonnees;

import java.util.LinkedList;
import java.util.List;

public class Carte {
	private int taille;
	private Case[][] carteMatrix;
	private int nLin, nCol;

	public Carte(int nbLignes, int nbColonnes, int tailleCases) {
		carteMatrix = new Case[nbLignes][nbColonnes];
		nLin = nbLignes;
		nCol = nbColonnes;
		taille = tailleCases;
	}
	public int getNbLignes() {
		return nLin;
	}
	public int getNbColonnes() {
		return nCol;
	}
	public int getTailleCases() {
		return taille;
	}
	public void insertCase(int ligne, int colonne, Case c) {
		carteMatrix[ligne][colonne] = c;
	}
	public Case getCase(int lig, int col) {
		return carteMatrix[lig][col];
	}

	public boolean voisinExiste(Case src, Direction dir) {
		boolean existe = false;

		switch(dir) {
			case EST: if(src.getColonne() + 1 >= nCol) existe = false;
					  else existe = true;
					  break;
			case SUD: if(src.getLigne() + 1 >= nLin) existe = false;
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

	public List<Case> getAllVoisins(Case src) {
		List<Case> voisins = new LinkedList<>();

		if (voisinExiste(src, Direction.NORD)) {
			voisins.add(getVoisin(src, Direction.NORD));
		}

		if (voisinExiste(src, Direction.SUD)) {
			voisins.add(getVoisin(src, Direction.SUD));
		}

		if (voisinExiste(src, Direction.OUEST)) {
			voisins.add(getVoisin(src, Direction.OUEST));
		}

		if (voisinExiste(src, Direction.EST)) {
			voisins.add(getVoisin(src, Direction.EST));
		}

		return voisins;
	}
}
