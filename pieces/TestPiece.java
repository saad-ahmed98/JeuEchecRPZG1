package pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import joueur.Partie;

	public class TestPiece {

	    ///testPartie
	    @Test
	    void testcaseExiste() {
	        Partie p = new Partie();
	        p.creerParti();
	        assertTrue(p.caseExiste(4, 5));///Test avec case existante
	        assertFalse(p.caseExiste(8, 1));///Case inexistante avec x inexistant
	        assertTrue(p.caseExiste(0, 1));///Test avec les limites x
	        assertTrue(p.caseExiste(0, 7));///Test avec les limites x et y
	        assertTrue(p.caseExiste(5, 6)); ///Test avec case existante
	        assertFalse(p.caseExiste(-1, 4)); ///Test avec x négatif
	        assertFalse(p.caseExiste(0, 8)); ///Test avec y qui n'existe pas
	        assertFalse(p.caseExiste(-1, -6)); ///Test avec coordonnées negatifs
	        assertTrue(p.caseExiste(3, 3)); /// Test milieu echec

	    }

	    ///testPiece
	    @Test
	    void testmouvementPion() {
	    	  Partie p=new Partie();
		        p.creerParti();
		        ///Test pion joueur blanc
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[1][0], p.getCases()[2][0]),1); //Déplacement deux cases
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[1][0], p.getCases()[3][0]),1); //Déplacement une case
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[1][0], p.getCases()[4][0]),3);//Déplacement trois cases hors porté
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[1][0], p.getCases()[2][1]),3); //Déplacement en diagonale alors qu'il n'y a pas de pion à manger
		   
		       p.getCases()[1][0].getP().deplacement(p.getCases()[1][0], p.getCases()[3][0]);
		       p.getCases()[6][1].getP().deplacement(p.getCases()[6][1], p.getCases()[4][1]);
		      
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[3][0], p.getCases()[4][1]),2); //Déplacement pour manger
		        p.getCases()[7][0].getP().deplacement(p.getCases()[7][0], p.getCases()[5][0]);
		        p.getCases()[5][0].getP().deplacement(p.getCases()[5][0], p.getCases()[4][0]);
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[3][0], p.getCases()[4][0]),5);//Déplacement alors qu'il y a un pion en face
		        p.getCases()[3][0].getP().deplacement(p.getCases()[3][0], p.getCases()[4][1]);
		        assertEquals(p.getJoueurs()[0].getPieces()[8].mouvement(p.getCases(), p.getCases()[4][1], p.getCases()[6][1]),3); //Déplacement deux cases pas en premier mouvement du pion
		        ///Test pion joueur noir
		        assertEquals(p.getJoueurs()[1].getPieces()[9].mouvement(p.getCases(), p.getCases()[6][2], p.getCases()[4][2]),1); //Déplacement deux cases premier mouvement du pion
		        assertEquals(p.getJoueurs()[1].getPieces()[9].mouvement(p.getCases(), p.getCases()[6][2], p.getCases()[5][2]),1); //Déplacement une case
		        assertEquals(p.getJoueurs()[1].getPieces()[9].mouvement(p.getCases(), p.getCases()[6][2], p.getCases()[5][3]),3); //Déplacement en diagonale alors qu'il n'y a pas de pion à manger
		        assertEquals(p.getJoueurs()[1].getPieces()[8].mouvement(p.getCases(), p.getCases()[6][2], p.getCases()[3][2]),3);//Déplacement trois cases hors porté
		        assertEquals(p.getJoueurs()[1].getPieces()[10].mouvement(p.getCases(), p.getCases()[6][0], p.getCases()[4][0]),5);//Déplacement deux cases mais une pièce de même couleur présente
		        p.getCases()[6][0].getP().deplacement(p.getCases()[6][0], p.getCases()[5][0]);
		        assertEquals(p.getJoueurs()[1].getPieces()[10].mouvement(p.getCases(), p.getCases()[5][0], p.getCases()[4][1]),2);//Manger un pion ennemi
		        assertEquals(p.getJoueurs()[1].getPieces()[10].mouvement(p.getCases(), p.getCases()[5][0], p.getCases()[4][0]),5);//Déplacement une case sur pièce de la même couleur
		        p.getCases()[5][0].getP().deplacement(p.getCases()[5][0], p.getCases()[5][1]);
		        assertEquals(p.getJoueurs()[1].getPieces()[10].mouvement(p.getCases(), p.getCases()[5][1], p.getCases()[4][0]),5);//Manger un pion allié
	    }
	    @Test
	    void testmouvementFou() {
	    	 Partie p=new Partie();
		        p.creerParti();
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[1][3]),5);//Déplacement sur un pion allié
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[4][6]),4);//Déplacement mais un pion allié gêne
		        
		        p.getCases()[1][3].getP().deplacement(p.getCases()[1][3], p.getCases()[2][3]);
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[1][3]),1);//Déplacement sur la case où était l'allié
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[4][6]),1);//Déplacement normal
		        p.getCases()[6][1].getP().deplacement(p.getCases()[6][1], p.getCases()[4][6]);
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[4][6]),2);//Déplacement pour manger
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[4][5]),3);//Déplacement hors porté
		        assertEquals(p.getJoueurs()[0].getPieces()[2].mouvement(p.getCases(), p.getCases()[0][2], p.getCases()[5][7]),4);//Déplacement mais pièce ennemi qui gêne
		        assertEquals(p.getJoueurs()[1].getPieces()[2].mouvement(p.getCases(), p.getCases()[7][2], p.getCases()[5][4]),4);//Déplacement allié gêne haut droite
		        assertEquals(p.getJoueurs()[1].getPieces()[5].mouvement(p.getCases(), p.getCases()[7][5], p.getCases()[5][3]),4);//Déplacement allié gêne haut gauche
	            
	    }
	    @Test
	    void testmouvementTour() {
	    	Partie p=new Partie();
	        p.creerParti();
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[0][0], p.getCases()[1][0]),5);//Déplacement sur un pion allié
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[0][0], p.getCases()[4][0]),4);//Déplacement mais un pion allié gêne 
	        p.getCases()[1][0].getP().deplacement(p.getCases()[1][0], p.getCases()[3][0]);
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[0][0], p.getCases()[2][0]),1);//Déplacement normal
	        p.getCases()[0][0].getP().deplacement(p.getCases()[0][0], p.getCases()[2][0]);
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[2][0], p.getCases()[2][2]),1);//Déplacement horizontal
	        p.getCases()[2][0].getP().deplacement(p.getCases()[2][0], p.getCases()[2][2]);
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[2][2], p.getCases()[6][2]),2);//Déplacement pour manger
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[2][2], p.getCases()[7][0]),3);//Déplacement hors portée
	        assertEquals(p.getJoueurs()[0].getPieces()[0].mouvement(p.getCases(), p.getCases()[2][2], p.getCases()[0][2]),4);//Déplacement haut pièce allié qui gêne
	       
	        
	        
	    }
	    @Test
	    void testmouvementCavalier() {
	    	Partie p=new Partie();
	        p.creerParti();
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[0][1], p.getCases()[2][0]),1);//Déplacement normal
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[0][1], p.getCases()[2][2]),1);//Déplacement normal
	        p.getCases()[0][1].getP().deplacement(p.getCases()[0][1], p.getCases()[2][0]);
	       
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[2][0], p.getCases()[4][1]),1);//Déplacement normal
	        p.getCases()[2][0].getP().deplacement(p.getCases()[2][0], p.getCases()[4][1]);
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[4][1], p.getCases()[6][2]),2);//Déplacement pour manger
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[4][1], p.getCases()[2][0]),1);//Déplacement pour revenir en arrière
	        p.getCases()[4][1].getP().deplacement(p.getCases()[4][1], p.getCases()[2][2]);
	        assertEquals(p.getJoueurs()[0].getPieces()[1].mouvement(p.getCases(), p.getCases()[2][2], p.getCases()[0][3]),5);//Déplacement sur un pion allié
	        
	    }
	    @Test
	    void testmouvementReine() {
	    	Partie p=new Partie();
	        p.creerParti();
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[1][4]),5);//Déplacement sur allié
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[1][3]),5);//Déplacement sur allié
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[5][3]),4);//Déplacement mais pièce sur le trajet
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[3][5]),3);//Déplacement hors porté
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[3][6]),4);//Déplacement mais pièce sur le trajet
	        p.getCases()[1][4].getP().deplacement(p.getCases()[1][4], p.getCases()[2][4]);
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[0][3], p.getCases()[3][6]),1);//Déplacement diagonal
	        p.getCases()[0][3].getP().deplacement(p.getCases()[0][3], p.getCases()[3][6]);
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[3][6], p.getCases()[6][3]),2);//Déplacement mangé diagonal
	        p.getCases()[3][6].getP().deplacement(p.getCases()[3][6], p.getCases()[6][3]);
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[6][3], p.getCases()[6][4]),2);//Déplacement mangé tout droit
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[6][3], p.getCases()[3][5]),3);//Déplacement hors porté
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[6][3], p.getCases()[5][2]),1);//Déplacement tout droit
	        p.getCases()[6][3].getP().deplacement(p.getCases()[6][3], p.getCases()[5][2]);
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[5][2], p.getCases()[5][3]),1);//Déplacement horizontal
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[5][2], p.getCases()[7][2]),4);//Déplacement bas pièce sur le trajet
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[5][2], p.getCases()[0][2]),4);//Déplacement haut pièce sur le trajet
	      
	        p.getCases()[5][2].getP().deplacement(p.getCases()[5][2], p.getCases()[5][3]);
	        assertEquals(p.getJoueurs()[0].getPieces()[3].mouvement(p.getCases(), p.getCases()[5][3], p.getCases()[7][1]),4);//Déplacement bas gauche pièce sur le trajet
	    }
	    @Test
	    void testmouvementRoi() {
	    	Partie p=new Partie();
	        p.creerParti();
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[0][4], p.getCases()[1][4]),5);//Déplacement sur allié
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[0][4], p.getCases()[1][3]),5);//Déplacement sur allié
	        p.getCases()[1][4].getP().deplacement(p.getCases()[1][4], p.getCases()[3][4]);
	        p.getCases()[1][3].getP().deplacement(p.getCases()[1][3], p.getCases()[3][3]);
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[0][4], p.getCases()[1][3]),1);//Déplacement normal
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[0][4], p.getCases()[1][4]),1);//Déplacement normal
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[0][4], p.getCases()[2][4]),3);//Déplacement hors porté
	        p.getCases()[6][5].getP().deplacement(p.getCases()[6][5], p.getCases()[4][5]);
	        p.getCases()[4][5].getP().deplacement(p.getCases()[4][5], p.getCases()[3][5]);
	        p.getCases()[3][5].getP().deplacement(p.getCases()[3][5], p.getCases()[2][5]);
	        p.getCases()[0][4].getP().deplacement(p.getCases()[0][4], p.getCases()[1][4]);
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[1][4], p.getCases()[2][5]),2);//Déplacement mangé diagonal
	        p.getCases()[6][7].getP().deplacement(p.getCases()[6][7], p.getCases()[4][7]);
	        p.getCases()[7][7].getP().deplacement(p.getCases()[7][7], p.getCases()[5][7]);
	        p.getCases()[5][7].getP().deplacement(p.getCases()[5][7], p.getCases()[5][4]);
	        p.getCases()[5][4].getP().deplacement(p.getCases()[5][4], p.getCases()[2][3]);
	        p.getCases()[2][3].getP().deplacement(p.getCases()[2][3], p.getCases()[2][4]);
	        assertEquals(p.getJoueurs()[0].getPieces()[4].mouvement(p.getCases(), p.getCases()[1][4], p.getCases()[2][4]),2);//Déplacement mangé tout droit
	        
	    
	    }
	    
	    @Test
	    void testenEchec() {
	    	 Partie p = new Partie();
		        p.creerParti();
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchec(p.getCases())); ///vérifier si c'est en echec dès le début
		        p.getCases()[7][3].getP().deplacement(p.getCases()[7][3], p.getCases()[1][4]);
		        assertTrue(((Roi)p.getCases()[0][4].getP()).enEchec(p.getCases())); ///vérifier l'echec en face
		        p.getCases()[1][4].getP().deplacement(p.getCases()[1][4], p.getCases()[1][5]);
		        assertTrue(((Roi)p.getCases()[0][4].getP()).enEchec(p.getCases())); ///vérifier l'echec en diagonale
		        p.getCases()[1][5].getP().deplacement(p.getCases()[1][5], p.getCases()[3][1]);
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchec(p.getCases())); ///vérifier si c'est en echec avec un pion qui défend le roi
	    }
	    
	    @Test
	    void testenEchecMat() {
	    	 Partie p = new Partie();
		        p.creerParti();
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchecMat(p.getCases())); ///vérifier si c'est en echec mat dès le début
		        p.getCases()[7][3].getP().deplacement(p.getCases()[7][3], p.getCases()[1][5]);
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchecMat(p.getCases())); //vérifier l'echec mat quand le roi est en echec simplement diagonal
		        p.getCases()[1][5].getP().deplacement(p.getCases()[1][5], p.getCases()[3][6]);
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchecMat(p.getCases())); //vérifier l'echec mat quand le roi est en echec mais qu'un pion peut le sauver
		        p.getCases()[3][6].getP().deplacement(p.getCases()[3][6], p.getCases()[1][6]);
		        p.getCases()[1][6].getP().deplacement(p.getCases()[1][6], p.getCases()[3][7]);
		        assertTrue(((Roi)p.getCases()[0][4].getP()).enEchecMat(p.getCases())); //Echec et mat
		        
		        p.getCases()[1][4].getP().deplacement(p.getCases()[1][4], p.getCases()[1][4]);
		        assertFalse(((Roi)p.getCases()[0][4].getP()).enEchecMat(p.getCases())); ///vérifier l'echec mat quand le roi est en echec simplement en face
		       
		        
		       //Test méthode du berger
		        Partie p2 = new Partie();
		        p2.creerParti();
		        p2.getCases()[6][4].getP().deplacement(p2.getCases()[6][4], p2.getCases()[4][4]);
		        p2.getCases()[1][4].getP().deplacement(p2.getCases()[1][4], p2.getCases()[3][4]);
		        p2.getCases()[7][5].getP().deplacement(p2.getCases()[7][5], p2.getCases()[4][2]);
		        p2.getCases()[0][1].getP().deplacement(p2.getCases()[0][1], p2.getCases()[2][2]);
		        p2.getCases()[7][3].getP().deplacement(p2.getCases()[7][3], p2.getCases()[3][7]);
		        p2.getCases()[0][6].getP().deplacement(p2.getCases()[0][6], p2.getCases()[2][5]);
		        p2.getCases()[3][7].getP().deplacement(p2.getCases()[3][7], p2.getCases()[1][5]);
		        assertTrue(((Roi)p2.getCases()[0][4].getP()).enEchecMat(p2.getCases())); //Echec et mat
		        
		        
		  
	    }
	    
	   ///testJoueur
	    @Test
	    void testAppartenance() {
	            Partie p=new Partie();
		        p.creerParti();
		       
	            assertTrue(p.getJoueurs()[0].verifierAppartenance(p.getCases()[1][1].getP()));//Test joueur 1 et piece lui appartenant
	            assertFalse(p.getJoueurs()[0].verifierAppartenance(p.getCases()[6][1].getP()));//Test joueur 1 et piece de l'ennemi
	            assertTrue(p.getJoueurs()[1].verifierAppartenance(p.getCases()[6][1].getP()));//Test joueur 2 et piece lui appartenant
	            assertFalse(p.getJoueurs()[1].verifierAppartenance(p.getCases()[4][3].getP()));//Test joueur 2 et pièce inexistante
	        }
	    
	    
	    @Test
	    void testverifierPiece() {
	    	Partie p = new Partie();
	        p.creerParti();
	        
	        assertTrue(p.getCases()[1][1].verifierPiece());//Case existante
	        assertTrue(p.getCases()[0][7].verifierPiece());//Limite x et y avec case existante
	        assertTrue(p.getCases()[1][6].verifierPiece());//Case existante
	        assertTrue(p.getCases()[6][3].verifierPiece());//Test case existante
	        assertTrue(p.getCases()[7][3].verifierPiece());//Test limite x 
	        assertTrue(p.getCases()[7][0].verifierPiece());//Test limite x et y
	       
	        
	        
	    }
	}
