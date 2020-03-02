package pieces;
import java.awt.Color;
import joueur.*;

public abstract class Piece {
	private Color couleur;
	private String id; //pour l'affichage
    private Piece p; //piece étant mangee (pour recuperation en cas d'erreur)

    //Constructeurs
	public Piece() {}
	
	public Piece(Color c, String id) {
		couleur=c;
		this.id=id;
	}
	
	public Piece(Color couleur) {
		this.couleur = couleur;
	}
	
	//Getters et Setters
	public String getId() {
		return id;
	}
    
    public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Piece getP() {
		return p;
	}

	public void setP(Piece p) {
		this.p = p;
	}

    //Deplacements et manger
	public abstract int mouvement(Case[][] cases, Case depart, Case arrive);
	
	public void deplacement(Case depart, Case arrive){
	    //Recupere la piece presente dans la case d'arrivee
		p=arrive.getP();
		//Deplace la piece dans la cas d'arrivee
        arrive.setP(this);
        //Vide la case de depart
        depart.setP(null);
	}
	
	public void annulerDeplac(Case depart, Case arrive){
	    //Insere la piece dans la case de depart
        arrive.setP(this);
        //Remet la piece sauvegardee dans la case d'arrivee
        depart.setP(p);
	}
	
    public boolean mangerPossible(Case arrive){
        //S'il y a une piece a manger mais qu'elle est de même couleur
	    if(arrive.verifierPiece() == true && !(arrive.getP().couleur.equals(this.couleur)))
	        return true;
	    else return false;
	}
}