package joueur;
import java.awt.Color;
import pieces.*;

public class Joueur {
	///variables
	private Color couleur; //couleur (noir ou blanc)
	private Piece[] pieces=new Piece[16]; //liste des propres pièces
	private Roi roi; //Roi, cette variable est initialisée après le debut de la partie
	
	///constructeur
	public Joueur(Color c) {
		couleur=c;
		initialiserPiece();
	}
	
	///getters et setters
	public Roi getRoi() {
		return roi;
	}
	
	public void setRoi(Roi roi) {
		this.roi = roi;
	}

	public Color getCouleur() {
		return couleur;
	}
	
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public Piece[] getPieces() {
		return pieces;
	}
	
	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}
	
	//methode pour initialiser les pieces du joueur dans l'ordre de leur placement initial sur l'echequier
	public void initialiserPiece() {
		pieces[0]=new Tour(couleur);
		pieces[1]=new Cavalier(couleur);
		pieces[2]=new Fou(couleur);
		pieces[3]=new Reine(couleur);
		pieces[4]=new Roi(couleur);
		pieces[5]=new Fou(couleur);
		pieces[6]=new Cavalier(couleur);
		pieces[7]=new Tour(couleur);
		for(int i=8;i<16;i++) 
			pieces[i]=new Pion(couleur);
	}
	
	//methode pour verifier si une piece en parametre appartient au joueur
	public boolean verifierAppartenance(Piece p) {
		if(p!=null) //si piece existe
		{
		if(this.couleur.equals(p.getCouleur())) //on compare les couleurs
			return true;
		else
			return false;
		}
		return false;
	}
}