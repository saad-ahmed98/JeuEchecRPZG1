package pieces;
import java.awt.Color;
import joueur.*;

public class Roi extends Piece{
	boolean echec; //True : roi en situation d'echec
	int xRoi;
	int yRoi;
	
	//Constructeur
	public Roi(Color c) {
		super(c,"R");
	}
	
	//Setter
	public void setCoord(int x,int y) {
    	xRoi=x;
    	yRoi=y;
	}
	
	//Deplacements
	public int mouvement(Case[][] cases, Case depart, Case arrive) {
    	int x= depart.getX()-arrive.getX(); //Calcul du nombre de cases de deplacement en x
    	int y= depart.getY()-arrive.getY(); //Calcul du nombre de cases de deplacement en y
    	
    	//Test de deplacements possibles
    	//Si le déplacement est d'une case dans toutes les directions
    	if((Math.abs(x)==Math.abs(y) && Math.abs(x)==1) || (x == 0 && Math.abs(y) == 1) || (y == 0 && Math.abs(x) == 1))
    	{
    	    //Test de piece presente sur la case d'arrivee
            if (arrive.verifierPiece() == false) //S'il n'y a pas de piece sur l'arrivee
                return 1;
            if(mangerPossible(arrive)) //Si une piece adverse (hors Roi) est presente
        	    return 2;
            else { //Si une piece non mangeable (piece alliee ou Roi adverse) est presente
            	return 5;
            }
        } 
        else { //Si le déplacement est incorrect
                return 3;
        }
    }

	@Override
	public void deplacement(Case depart, Case arrive){
		super.setP(arrive.getP()); //Recupere la piece presente dans la case d'arrivee
        arrive.setP(this); //Deplace la piece dans la cas d'arrivee
        depart.setP(null); //Vide la case de depart
    	setCoord(arrive.getX(),arrive.getY());
	}

	@Override
	public void annulerDeplac(Case depart, Case arrive){
        arrive.setP(this); //Piece remise a sa case de depart
        depart.setP(super.getP()); //Piece enregistree remise dans la case d'arrivee
    	setCoord(arrive.getX(),arrive.getY());
	}
	
	//Echec + Echec et mat
	public boolean enEchec(Case[][] c) {
		for (int i = 0; i < c.length; i++) { //Recupere les possibilites d'echec pour toutes les pieces
            for (int j = 0; j < c[i].length; j++) {
            	if(c[i][j].getP()!=null){
                    if(c[i][j].getP().mouvement(c, c[i][j], c[yRoi][xRoi])==2){ //Si une piece met le roi en echec
                	return true;
                    }
                }
            }
        }
		return false; //S'il n'y a pas d'echec
	}
	
	public boolean enEchecMat(Case[][] c) {
	    for (int i = 0; i < c.length; i++) {
	        for (int j = 0; j < c[i].length; j++) {
	            if (c[i][j].getP() != null) {
	                //si piece de la meme couleur que le roi
	                if (c[i][j].getP().getCouleur().equals(this.getCouleur())) {
	                    //tester tous les mouvements possibles
	                    for (int k = 0; k < c.length; k++) {
	                        for (int l = 0; l < c[k].length; l++) {
	                            if (c[i][j].getP().mouvement(c, c[i][j], c[k][l]) == 2 || c[i][j].getP().mouvement(c, c[i][j], c[k][l]) == 1) {
	                                c[i][j].getP().deplacement(c[i][j], c[k][l]);
	                                if (!enEchec(c)) {
	                                    c[k][l].getP().annulerDeplac(c[k][l], c[i][j]);
	                                    return false;
	                                }
	                                else c[k][l].getP().annulerDeplac(c[k][l], c[i][j]);
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	    //si le roi toujours en echec a la fin alors echec et mat
	    return true;
	}
}