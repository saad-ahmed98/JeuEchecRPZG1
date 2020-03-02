package pieces;
import java.awt.Color;
import joueur.*;

public class Tour extends Piece {

    //Constructeur
    public Tour(Color c) {
        super(c, "T");
    }

    //Mouvement
    public int mouvement(Case[][] cases, Case depart, Case arrive) {
        int x = depart.getX() - arrive.getX(); //Calcul du nombre de cases de deplacement en x
        int y = depart.getY() - arrive.getY(); //Calcul du nombre de cases de deplacement en y
        
        //Test de deplacement possible
        if (x == 0 && y != 0) //Deplacement Vertical
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
        } else if (y == 0 && x != 0) //Deplacement Horizontal
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