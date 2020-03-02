package pieces;
import java.awt.Color;
import joueur.*;

public class Reine extends Piece {
    
    //Constructeur
    public Reine(Color c) {
        super(c, "D");
    }

    //Mouvement
    public int mouvement(Case[][] cases, Case depart, Case arrive) {
        int x = depart.getX() - arrive.getX(); //Calcul du nombre de cases de deplacement en x
        int y = depart.getY() - arrive.getY(); //Calcul du nombre de cases de deplacement en y
        int j = 0;
        
        //Test de deplacement possible
        if (Math.abs(x) == Math.abs(y)) { //Si le déplacement est diagonal
            if (x < 0 && y > 0) //Diagonal vers haut droite 
            {
                for (int i = depart.getX() + 1; i < arrive.getX(); i++) {
                    j++;
                    if (cases[depart.getY() - j][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
            if (x > 0 && y > 0) //Diagonal vers haut gauche
            {
                for (int i = depart.getX() - 1; i > arrive.getX(); i--) {
                    j++;
                    if (cases[depart.getY() - j][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
            if (x < 0 && y < 0) //Diagonal vers bas droite
            {
                for (int i = depart.getX() + 1; i < arrive.getX(); i++) {
                    j++;
                    if (cases[depart.getY() + j][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
            if (x > 0 && y < 0) //Diagonal vers bas gauche
            {
                for (int i = depart.getX() - 1; i > arrive.getX(); i--) {
                    j++;
                    if (cases[depart.getY() + j][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
            
            //Test de piece presente sur la case d'arrivee
            if (arrive.verifierPiece() == false) //S'il n'y a pas de piece sur l'arrivee
                return 1;
            if (mangerPossible(arrive)) //Si une piece adverse (hors Roi) est presente
                return 2;
            else { //Si une piece non mangeable (piece alliee ou Roi adverse) est presente
                return 5;
            }
        }
        
        if (x == 0 && y != 0) //Si le déplacement est vertical
        {
            if (y < 0) //Vertical haut
            {
                for (int i = depart.getY() + 1; i < arrive.getY(); i++) {
                    if (cases[i][depart.getX()].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            } else if (y > 0) //Vertical bas
            {
                for (int i = depart.getY() - 1; i > arrive.getY(); i--) {
                    if (cases[i][depart.getX()].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
        } else if (y == 0 && x != 0) //Si le déplacement est horizontal
        {
            if (x < 0) //Horizontal droite
            {
                for (int i = depart.getX() + 1; i < arrive.getX(); i++) {
                    if (cases[depart.getY()][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            } else if (x > 0) //Horizontal gauche
            {
                for (int i = depart.getX() - 1; i > arrive.getX(); i--) {
                    if (cases[depart.getY()][i].verifierPiece() == true) { //Si une piece se situe sur le chemin
                        return 4;
                    }
                }
            }
        } 
        else { //Si le déplacement est incorrect
            return 3;
        }
        
        //Test de piece presente sur la case d'arrivee
        if (arrive.verifierPiece() == false) //S'il n'y a pas de piece sur l'arrivee
            return 1;
        if (mangerPossible(arrive)) //Si une piece adverse (hors Roi) est presente
            return 2;
        else { //Si une piece non mangeable (piece alliee ou Roi adverse) est presente
            return 5;
        }
    }
}